package com.once.facturas.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.websocket.server.PathParam;

import com.once.facturas.model.Producto;
import com.once.facturas.model.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * MainController es el controlador de la aplicación Facturas,
 * que es una aplicación tipo MVC (Modelo Vista Controlador)
 * 
 * 
 */

@RestController
@RequestMapping(value = "/productos")
class ProductoController {
    
    @Autowired
    ProductoRepository pr;


    @GetMapping("/")
    public Iterable<Producto> getAllProductos(){
        return pr.findAll();
    }

    @GetMapping("{id}/")
    public Producto getProducto(
            @PathVariable("id") Long id
        ){
            
         try {
            return pr.findById(id).get(); 
         } catch (NoSuchElementException e) {
             return null;
         }
            
        }


    @GetMapping("/hello") // Escucho al GET en /hello
    @ResponseBody // Haré un body html para devolver la página completa
    public String hello() { // Método para devolver un string para responsebody
        return "Hola Mundo"; // Devuelvo Hola Mundo
    }

    @GetMapping("/count") // Escucho al GET en /count
    @ResponseBody // Haré un body html para devolver la página completa
    public String count() { // Método para devolver un string con responsebody
        return "Tengo "+String.valueOf(pr.count())+" productos"; // Devuelvo el número de productos desde pr.count()
    }

}