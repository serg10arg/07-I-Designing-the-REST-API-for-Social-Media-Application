package com.example.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@RestController
public class FilteringController {

//    @GetMapping("/filtering")
//    public SomeBean filtering() {
//        return new SomeBean("value1", "value2", "value3");
//    }
//
//    @GetMapping("/filtering-list")
//    public List<SomeBean> filteringList() {
//        return Arrays.asList(new SomeBean("value1", "value2", "value3"),
//                new SomeBean("value4", "value5", "value6"));
//    }

    @GetMapping("/filtering")
    public MappingJacksonValue filtering() {
        // Paso 0: Obtener el bean con los datos.
        SomeBean someBean = new SomeBean("value1", "value2", "value3");

        // Paso 1: Crear el contenedor que llevará tanto los datos como los filtros.
        // Cambiamos el tipo de retorno del método de SomeBean a MappingJacksonValue.
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);

        // Paso 2: Definir la regla de filtrado. Queremos mostrar solo field1 y field3.
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");

        // Paso 3: Crear un proveedor de filtros que asocie nuestra regla con un nombre único.
        // Este nombre ("SomeBeanFilter") es el que usaremos para conectar con el bean.
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        // Paso 4: Asignar el proveedor de filtros al contenedor.
        mappingJacksonValue.setFilters(filters);

        // Paso 5: Devolver el contenedor. Spring Boot y Jackson se encargarán del resto.
        return mappingJacksonValue;
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue filteringList() {
        List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2", "value3"),
                new SomeBean("value4","value5","value6"));

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");

        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

}
