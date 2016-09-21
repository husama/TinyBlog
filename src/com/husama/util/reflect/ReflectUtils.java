package com.husama.util.reflect;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by husama on 16-9-20.
 */
public class ReflectUtils {

    public static Field[] getAllFields(Class<?> clazz){
        List<Field> fieldList = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        if(fields != null && fields.length > 0){
            fieldList.addAll(Arrays.asList(fields));
        }

        Class<?> superClass = clazz.getSuperclass();
        if (superClass != Object.class) {
            Field[] superFields = getAllFields(superClass);
            if (superFields != null&& superFields.length > 0) {
                for(Field field:superFields){
                    if(!contains(fieldList, field)){
                        fieldList.add(field);
                    }
                }
            }
        }
        Field[] result=new Field[fieldList.size()];
        fieldList.toArray(result);
        return result;

    }

    private static boolean contains(List<Field> fieldList, Field field){
        for(Field temp:fieldList){
            if(temp.equals(field)){
                return true;
            }
        }
        return false;
    }
}
