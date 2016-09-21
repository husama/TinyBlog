package com.husama.annotation.dao;

import java.lang.annotation.*;

/**
 * Created by husama on 16-9-20.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ManyToMany {
}
