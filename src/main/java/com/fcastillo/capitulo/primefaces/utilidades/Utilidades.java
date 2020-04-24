/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.capitulo.primefaces.utilidades;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author fcastillo
 */
@Named(value = "utilidades")
@RequestScoped
public class Utilidades {

    public static Integer espaciosFinales(String text) {
        Integer count = 0;
        int finalPos = text.length() - 1;
        boolean jump = false;

        while (finalPos > 0 && jump == false) {
            if (text.charAt(finalPos) == ' ') {
                count++;
            } else {
                jump = true;
            }
            finalPos--;
        }
        return count;
    }

}
