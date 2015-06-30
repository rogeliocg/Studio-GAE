package com.adquem.backend.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by Rogelio on 6/30/15.
 */
@Entity
public class Guestbook {
    @Id
    public String book;
}
