/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imran;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import static java.lang.System.out;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Imran
 */
public class MySessionListener implements HttpSessionListener , HttpSessionAttributeListener{

    @Override
    public void sessionCreated(HttpSessionEvent se) {
       
        
        out.println("\n session created");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        
        out.println("\n session destroyed");
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
       
        out.println("\n name  "+event.getName());
        
        out.println("\n value "+event.getValue());
        
        HttpSession hs   = event.getSession();
        
        out.println("\n new Value "+hs.getAttribute(event.getName()));
        
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        
        
        
        out.println("\n attribute removed from session");
        
        out.println("\n name  "+event.getName());
        
        out.println("\n value "+event.getValue());
        
       
        
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        
    }
    
}
