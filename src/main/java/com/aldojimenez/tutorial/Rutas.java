package com.aldojimenez.tutorial;

import com.aldojimenez.tutorial.models.Libro;
import com.aldojimenez.tutorial.models.Producto;
import com.aldojimenez.tutorial.models.UserData;
import com.aldojimenez.tutorial.myBeans.MiBean;
import com.aldojimenez.tutorial.myBeans.MiComponente;
import com.aldojimenez.tutorial.servicios.IOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
public class Rutas {

    private IOrderService orderService;
    private MiBean miBean;
    private MiComponente miComponente;

    public Rutas(IOrderService orderService, MiBean miBean, MiComponente miComponente) {
        this.orderService = orderService;
        this.miBean = miBean;
        this.miComponente = miComponente;
    }

    private static final Logger logger = LoggerFactory.getLogger(Rutas.class);

    @GetMapping("/hola")
    String miPrimeraRuta() {
        return "Hola mundo desde Spring Controller :)";
    }

    @GetMapping("/libro/{id}/editorial/{editorial}")
    String leerLibro(@PathVariable int id, @PathVariable String editorial) {
        return "Leyendo el libro: " + id + ", editorial: " + editorial;
    }

    @GetMapping("/libro2/{id}")
    String leerLibro2(@PathVariable int id, @RequestParam String gato, @RequestParam String editorial) {
        return "Leyendo el libro: " + id + ", params: " + gato + ", editorial: " + editorial;
    }

    @PostMapping("/libro")
    String guardarLibro(@RequestBody Libro libro) {
        logger.debug("libro {} valor {}", libro.nombre, libro.editorial);

        return "libro guardado";
    }

    @GetMapping("/saludar")
    @ResponseStatus(value = HttpStatus.ACCEPTED, reason = "Fue movida a la version 2 de la API")
    String miSegundaRutaConStatus() {
        return "Aprendiendo status Http en Spring Boot";
    }


    @GetMapping("/animales/{lugar}")
    public ResponseEntity<String> getAnimales(@PathVariable String lugar) {
        if (lugar.equals("granja")) {
            return ResponseEntity.status(HttpStatus.OK).body("Caballo, oveja, vaca, gallina");
        } else if (lugar.equals(("selva"))) {
            return ResponseEntity.status(HttpStatus.OK).body("mono, gorila, puma");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lugar no válido");
        }
    }

    @GetMapping("/calcular/{numero}")
    public int getCalcular(@PathVariable int numero) {
        throw new NullPointerException("la clave del usuario es pasword1234 y no deberia leerse en postman");
    }

    @GetMapping("/userData")
    public ResponseEntity<String> getUserData() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body("{\"name\": \"mary\"}"); // este formato json crudo es tedioso de escribir
        // asi que se anota un ejemplo de buena practica abajo en el siguente metodo
    }

    @GetMapping("/userData/V2")
    public Map<String, Map<String, Object>> getUserDataV2() {
        return Map.of("user", Map.of("name", "mary", "age", 25));
    } // el método de abajo es mas eficiente ya que usa java (clases) para que sea más fácil

    @GetMapping("/userData/v3")
    public UserData getUserDataV3() {
        return new UserData("mary", 25, "alameda 1234");
    }


    // este es mi primer servicio de input / output
    @PostMapping("/order")
    public String crearOrden(@RequestBody List<Producto> products) {
        orderService.saveOrder(products);
        return "Productos guardados";
    }

    @GetMapping("/mibean")
    public String saludarDesdeBean() {
        miBean.saludar();
        return "Completado";
    }

    @GetMapping("/micomponente")
    public String saludarDesdeComponente() {
        miComponente.saludarDesdeComponente();
        return "Completado";
    }


}
