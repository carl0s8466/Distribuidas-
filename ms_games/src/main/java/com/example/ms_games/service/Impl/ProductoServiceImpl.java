package com.example.ms_games.service.Impl;

import com.example.ms_games.service.ProductoService;
import com.example.ms_games.entity.Categoria;
import com.example.ms_games.entity.Producto;
import com.example.ms_games.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String categoriaServiceUrl = "http://ms-catalogo-service/categoria/";

    @Override
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    @Override
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizar(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Optional<Producto> listarPorId(Integer id) {
        return productoRepository.findById(id);
    }

    @Override
    public void eliminarPorId(Integer id) {
        productoRepository.deleteById(id);
    }

    @Override
    public Optional<Producto> obtenerProductoConNombreCategoria(Integer id) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if (productoOptional.isPresent()) {
            Producto producto = productoOptional.get();
            String categoriaNombre = obtenerNombreCategoria(producto.getCategoriaId());
            producto.setCategoriaNombre(categoriaNombre);
            return Optional.of(producto);
        }
        return Optional.empty();
    }

    private String obtenerNombreCategoria(Integer categoriaId) {
        try {
            Categoria categoria = restTemplate.getForObject(categoriaServiceUrl + categoriaId, Categoria.class);
            if (categoria != null) {
                return categoria.getNombre();
            }
        } catch (Exception e) {
            return "Categoría no encontrada";
        }
        return null;
    }
}