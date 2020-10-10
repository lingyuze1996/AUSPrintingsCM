/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ling.yuze.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedProperty;
import ling.yuze.managedbean.UserManagedBean;

/**
 *
 * @author Roger
 */
@ApplicationScoped
public class CMApplication {
    @ManagedProperty(value="#{userManagedBean}")
    UserManagedBean userManagedBean;
}
