package com.ddong.item.controller;

import com.ddong.item.pojo.Category;
import com.ddong.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @RequestMapping("list")
    public ResponseEntity queryCategoryByPid(@RequestParam(defaultValue = "0") Long pid){
        try {
        List<Category> list=categoryService.queryCategoryByPid(pid);
        return  ResponseEntity.ok(list);
        }catch (Exception e){
            e.printStackTrace();

        }
        return  ResponseEntity.notFound().build();
    }
    @PostMapping("add")
    public ResponseEntity addCategory(@RequestBody Category category){
        try {
            Category ct=categoryService.addCategory(category);
            return  ResponseEntity.ok(ct);
        }catch (Exception e){
            e.printStackTrace();

        }
        return  ResponseEntity.badRequest().build();
    }
    @PostMapping("update")
    public ResponseEntity updateCategory(@RequestBody Category category){
        try {
            categoryService.updateCategory(category);
            return  ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();

        }
        return  ResponseEntity.badRequest().build();
    }
    @GetMapping("delete")
    public ResponseEntity deleteCategory(@RequestParam Long id){
        try {
            categoryService.deleteCategory(id);
            return  ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();

        }
        return  ResponseEntity.badRequest().build();
    }
}
