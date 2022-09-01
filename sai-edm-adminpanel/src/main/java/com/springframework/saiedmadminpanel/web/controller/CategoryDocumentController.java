package com.springframework.saiedmadminpanel.web.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.springframework.saiedmadminpanel.web.dto.CategoryDocumentDto;
import com.springframework.saiedmadminpanel.web.factories.CategoryDocumentFactory;
import com.springframework.saiedmadminpanel.web.store.entities.CategoryDocumentEntity;
import com.springframework.saiedmadminpanel.web.store.repositories.CategoryDocumentRepository;
import com.springframework.saiedmadminpanel.web.exceptions.NotFoundException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@RequiredArgsConstructor
@Transactional
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryDocumentController {

    CategoryDocumentRepository categoryDocumentRepository;
    CategoryDocumentFactory categoryDocumentFactory;
    ModelMapper modelMapper;

    public static final String FETCH_CATEGORIES = "/api/directory/categories/{page}/{size}";
    public static final String GET_CATEGORY = "/api/directory/categories/{categoryId}";
    public static final String UPDATE_CATEGORY = "/api/directory/category/{categoryId}";
    public static final String CREATE_CATEGORY = "/api/directory/category/";
    public static final String DELETE_CATEGORY = "/api/directory/category/{categoryId}";


    @GetMapping(FETCH_CATEGORIES)
    public List<CategoryDocumentDto> getCategories(@PathVariable("page") int page,
                                                   @PathVariable("size") int size){

        Pageable paging = PageRequest.of(page, size);
        Page<CategoryDocumentEntity> entities = categoryDocumentRepository.findAll(paging);
        return entities.getContent().stream().
                map(categoryDocumentFactory::makeCategoryDocumentDto).
                toList();
    }

    @GetMapping(GET_CATEGORY)
    public CategoryDocumentDto getCategoryById(@PathVariable Integer categoryId){

        CategoryDocumentEntity entities = categoryDocumentRepository
                .findById(categoryId)
                .orElseThrow(() ->
                        new NotFoundException(String.format("Категория с идентификатором \"%s\" не найден.", categoryId))
                );

        return categoryDocumentFactory.makeCategoryDocumentDto(entities);
    }

    @PostMapping(CREATE_CATEGORY)
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDocumentDto createCategory(@RequestBody CategoryDocumentDto categoryDocumentDto) throws ParseException {

        CategoryDocumentEntity categoryDocumentEntity = convertToEntity(categoryDocumentDto);
        System.out.println(categoryDocumentEntity.getId());
        CategoryDocumentEntity categorySaved = categoryDocumentRepository.save(categoryDocumentEntity);
        System.out.println(categorySaved.getId());
        return convertToDto(categorySaved);
    }

    @DeleteMapping(DELETE_CATEGORY)
    public Map<String, Boolean> deleteCategory(@PathVariable("categoryId") Integer categoryId) throws NotFoundException{
        CategoryDocumentEntity entity = categoryDocumentRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Нет такого ID"));
        categoryDocumentRepository.delete(entity);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


    private CategoryDocumentEntity convertToEntity(CategoryDocumentDto categoryDocumentDto){
        return modelMapper.map(categoryDocumentDto, CategoryDocumentEntity.class);
    }

    private CategoryDocumentDto convertToDto(CategoryDocumentEntity categoryDocumentEntity){
        return modelMapper.map(categoryDocumentEntity, CategoryDocumentDto.class);
    }
}
