package com.adquem.backend.model;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

import java.util.Date;

/**
 * Created by Rogelio on 6/30/15.
 */
@Entity
public class Greeting {

    @Parent Key<Guestbook> theBook;
    @Id public Long id;

    public String author_email;
    public String author_id;
    public String content;
    @Index public Date date;

    /**
     * Simple constructor just sets the date
     **/
    public Greeting() {
        date = new Date();
    }

    /**
     * A connivence constructor
     **/
    public Greeting(String book, String content) {
        this();
        if( book != null ) {
            theBook = Key.create(Guestbook.class, book);  // Creating the Ancestor key
        } else {
            theBook = Key.create(Guestbook.class, "default");
        }
        this.content = content;
    }

    /**
     * Takes all important fields
     **/
    public Greeting(String book, String content, String id, String email) {
        this(book, content);
        author_email = email;
        author_id = id;
    }
}
