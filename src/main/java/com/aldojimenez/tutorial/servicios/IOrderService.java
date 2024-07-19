package com.aldojimenez.tutorial.servicios;

import com.aldojimenez.tutorial.models.Producto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IOrderService {

    public void saveOrder(List<Producto> productos);

}
