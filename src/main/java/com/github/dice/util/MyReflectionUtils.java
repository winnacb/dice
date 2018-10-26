package com.github.dice.util;

import com.google.common.reflect.TypeToken;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MyReflectionUtils extends ReflectionUtils {


    private static final Map<Class<?>, Map<String, Field>> fieldCache = new ConcurrentHashMap<Class<?>, Map<String, Field>>();


    private static final Map<Class<?>, Map<String, Method>> methodCache = new ConcurrentHashMap<Class<?>, Map<String, Method>>();


    @SuppressWarnings("rawtypes")
    public static Field[] getAllDeclareFields(Class clazz){
        if (clazz.getSuperclass() == Object.class) {
            return clazz.getDeclaredFields();
        }

        Set<String> names = new HashSet<String>();
        List<Field> list = new ArrayList<Field>();
        Class<?> searchType = clazz;
        while (!Object.class.equals(searchType) && searchType != null) {
            Field[] fields = searchType.getDeclaredFields();
            for (Field field : fields) {
                if (names.contains(field.getName())) {
                    continue;
                }
                list.add(field);
            }
            searchType = searchType.getSuperclass();
        }
        Field[] fields = new Field[list.size()];
        return list.toArray(fields);

    }

    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    public static Object getFieldValue(Object target, String fieldName) {
        if (target == null) {
            return null;
        }
        if (isBlank(fieldName)) {
            return null;
        }
        if (target instanceof Map) {
            return ((Map<?, ?>) target).get(fieldName);
        }
        Class<?> clazz = null;
        if (target instanceof Class) {
            clazz = (Class<?>) target;
        }else {
            clazz = target.getClass();
        }

        Map<String, Field> fieldMap = fieldCache.get(clazz);
        if (fieldMap == null || fieldMap.containsKey(fieldName)) {
            synchronized (clazz) {
                fieldMap = fieldCache.get(clazz);
                if (fieldMap == null || fieldMap.containsKey(fieldName)) {
                    Field field = findField(clazz, fieldName);
                    if (field != null) {
                        field.setAccessible(true);
                        if (fieldMap == null) {
                            fieldMap = new ConcurrentHashMap<String, Field>();
                            fieldCache.put(clazz, fieldMap);
                        }
                    }
                }
            }
        }

        if(fieldMap == null || !fieldMap.containsKey(fieldName)){
            throw new RuntimeException(String.format("class [%s] no attributes [%s]", clazz.getCanonicalName(), fieldName));
        }

        Field filed = fieldMap.get(fieldName);

        try {
            return filed.get(target);
        } catch (Exception e) {
            String message = String.format("class [%s] reflection access attribute [%s] exception!", clazz.getCanonicalName(), fieldName);
            throw new RuntimeException(message, e);
        }

    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void setFieldValue(Object target, String fieldName, Object fieldValue){

        if(target == null || isBlank(fieldName)){
            return;
        }
        if(target instanceof Map){
            ((Map)target).put(fieldName, fieldValue);
            return;
        }

        Class<?> clazz = null;
        if(target instanceof Class){
            clazz = (Class<?>)target;
        } else {
            clazz = target.getClass();
        }

        Map<String, Field> filedMap = fieldCache.get(clazz);
        if(filedMap == null || !filedMap.containsKey(fieldName)){

            synchronized (clazz) {
                filedMap = fieldCache.get(clazz);
                if(filedMap == null || !filedMap.containsKey(fieldName)){
                    Field field = findField(clazz, fieldName);
                    if(field != null){
                        field.setAccessible(true);
                        if(filedMap == null){
                            filedMap = new ConcurrentHashMap<String, Field>();
                            fieldCache.put(clazz, filedMap);
                        }
                        filedMap.put(fieldName, field);
                    }
                }
            }
        }

        if(filedMap == null || !filedMap.containsKey(fieldName)){
            throw new RuntimeException(String.format("class [%s] no attributes [%s]", clazz.getCanonicalName(), fieldName));
        }

        Field filed = filedMap.get(fieldName);

        try {
            filed.set(target, fieldValue);
        } catch (Exception e) {
            String message = String.format("class [%s] reflection access attribute [%s] exception!", clazz.getCanonicalName(), fieldName);
            throw new RuntimeException(message, e);
        }

    }


    public static Object invokeStaticMethod(Class<?> clazz, String methodName, Object...objects) {
        return invokeMethod(clazz, methodName, objects);
    }


    public static Object invokeMethod(Object target, String methodName, Object[] objects) {
        if(target == null || isBlank(methodName)){
            return null;
        }

        Class<?> clazz = null;
        if(target instanceof Class){
            clazz = (Class<?>)target;
        } else {
            clazz = target.getClass();
        }

        Map<String, Method> methodMap = methodCache.get(clazz);
        if(methodMap == null || !methodMap.containsKey(methodName)){
            synchronized (clazz) {
                methodMap = methodCache.get(clazz);
                if(methodMap == null || !methodMap.containsKey(methodName)){
                    Method method = findMethod(clazz, methodName, new Class<?>[]{});
                    if(method != null){
                        method.setAccessible(true);
                        if(methodMap == null){
                            methodMap = new ConcurrentHashMap<String, Method>();
                            methodCache.put(clazz, methodMap);
                        }
                        methodMap.put(methodName, method);
                    }
                }
            }

        }

        if(methodMap == null || !methodMap.containsKey(methodName)){
            throw new RuntimeException(String.format("class [%s] nonexistent method [%s]", clazz.getCanonicalName(), methodName));
        }

        Method method = methodMap.get(methodName);

        try {
            return method.invoke(target, objects);
        } catch (Exception e) {
            String message = String.format("class [%s] reflection access method [%s] exception!", clazz.getCanonicalName(), methodName);
            throw new RuntimeException(message, e);
        }

    }

    public static <A extends Annotation> Field findUniqueFieldWithAnnotation(Class<?> clz, final Class<A> type) {
        final List<Field> fields = new ArrayList<Field>();

        doWithFields(clz, new FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                fields.add(field);
            }
        }, new FieldFilter() {
            @Override
            public boolean matches(Field field) {
                return field.isAnnotationPresent(type);
            }
        });

        if (fields.size() > 1) {
                throw new IllegalStateException("be commented" + type.getSimpleName() + "The declared domain is not unique");
        } else if (fields.size() == 1) {
            return fields.get(0);
        }
        return null;

    }


    public static void doWithDeclaredFields(Class<?> clazz, FieldCallback fc, FieldFilter ff) throws IllegalArgumentException {
        if (clazz == null || clazz == Object.class) {
            return;
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (ff != null && !ff.matches(field)) {
                continue;
            }
            try {
                fc.doWith(field);
            } catch (IllegalAccessException ex) {
                throw new IllegalStateException("illegal access attribute '" + field.getName() + "': " + ex);
            }
        }
    }


    public static Field getFirstDeclaredFieldWith(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(annotationClass)) {
                return field;
            }
        }
        return null;
    }


    public static Field[] getDeclaredFieldsWith(Class<?> clazz, final Class<? extends Annotation> annotationClass) {
        final List<Field> fields = new ArrayList<Field>();
        ReflectionUtils.doWithFields(clazz, new FieldCallback() {

            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                if (field.isAnnotationPresent(annotationClass)) {
                    fields.add(field);
                }
            }
        });
        return fields.toArray(new Field[0]);
    }


    public static Method getFirstDeclaredMethodWith(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotationClass)) {
                return method;
            }
        }
        return null;
    }


    public static Method[] getDeclaredMethodsWith(Class<?> clazz, Class<? extends Annotation> annotionClass) {
        List<Method> methods = new ArrayList<Method>();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotionClass)) {
                methods.add(method);
            }
        }
        return methods.toArray(new Method[0]);
    }


    public static Method[] getDecllaredGetMethodsWith(Class<?> clazz, Class<? extends Annotation> annotationClass){
        List<Method> methods = new ArrayList<Method>();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getAnnotation(annotationClass) == null) {
                continue;
            }
            if (method.getReturnType().equals(void.class)) {
                continue;
            }
            if (method.getParameterTypes().length > 0) {
                continue;
            }
            methods.add(method);
        }
        return methods.toArray(new Method[0]);
    }


    public static Set<Class<?>> getSupperClass(Class<?> clazz){
        Set<Class<?>> set = new HashSet<>(TypeToken.of(clazz).getTypes().rawTypes());
        set.remove(Object.class);
        return set;
    }

}