package com.aldojimenez.tutorial.myBeans;

import com.aldojimenez.tutorial.models.Producto;
import com.aldojimenez.tutorial.servicios.IOrderService;
import com.aldojimenez.tutorial.servicios.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class MisPrimerosBeans {

    @Bean
    public MiBean crearMiBean() {
        return new MiBean();
    }


    @Bean
    public IOrderService instanciarOrderService() {
        boolean esProduccion = true;

        //base de datos de produccion
        if (esProduccion) {
            return new OrderService();
        } {
            // base de datos local
            return new IOrderService() {
                @Override
                public void saveOrder(List<Producto> productos) {
                    System.out.println("Guardando base de datos dummy (local)");
                }
            };
        }
    }

}
