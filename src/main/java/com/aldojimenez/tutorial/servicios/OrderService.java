package com.aldojimenez.tutorial.servicios;

import com.aldojimenez.tutorial.models.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class OrderService implements IOrderService {

    @Value("${misurls.database}")
    private String dataBaseUrl;

    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public OrderService() {
    }

    public void saveOrder(List<Producto> productos) {
        System.out.println("Guardando en la base de datos, la url: " + dataBaseUrl);

        productos.forEach(producto -> logger.debug("el nombre del producto: {}", producto.nombre));
    }
}
