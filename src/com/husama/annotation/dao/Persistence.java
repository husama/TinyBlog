package com.husama.annotation.dao;

import java.lang.annotation.*;

/**
 * Created by husama on 16-9-19.
 */

//not persistence annotation in field
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Persistence {

}
