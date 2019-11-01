package com.finance.api.resource;

import com.finance.api.model.Category;
import com.finance.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> find() {
        return categoryRepository.findAll();
    }

    @PostMapping
    private ResponseEntity<Category> create(@RequestBody Category category, HttpServletResponse response) {
        Category categorySaved = categoryRepository.save(category);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(categorySaved.getId()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(categorySaved);
    }

    @RequestMapping("/{id}")
    private Category findById(@PathVariable Long id){
        return categoryRepository.findById(id)
                .orElse(null);
    }
}
