package com.ddong.item.controller;

import com.ddong.common.exception.SysException;
import com.ddong.common.pojo.PageResult;
import com.ddong.item.pojo.Brand;
import com.ddong.item.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("brand")
@Slf4j
public class BrandController {
private Logger logger=Logger.getLogger(this.getClass().getName());
@Autowired
private BrandService brandService;
    @GetMapping("pageList")
    public ResponseEntity pageList(  @RequestParam(defaultValue = "1")Long pageIndex, @RequestParam(defaultValue = "5")Long pageSize, String key, String sortBy, Boolean desc){
      try {
     PageResult pr= brandService.pageList(pageIndex,pageSize,key,sortBy,desc);
     return  ResponseEntity.ok(pr);
      }catch (Exception e){
    logger.info("品牌分页失败"+e.getMessage());
      }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("add")
    public ResponseEntity add(Brand brand){
        try {
            brandService.add(brand);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  ResponseEntity.badRequest().build();
    }
    @PutMapping("add")
    public ResponseEntity update(Brand brand){
        try {
            brandService.update(brand);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  ResponseEntity.badRequest().build();
    }
    @PostMapping("delete")
    public  ResponseEntity delete(@RequestParam(value = "id")Long id){
        try {
            brandService.delete(id);
          return   ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  ResponseEntity.badRequest().build();
    }
    @GetMapping("cid/{cid}")
    public  ResponseEntity queryByCid(@PathVariable Long cid){
        try {
           List<Brand> brandList= brandService.queryByCid(cid);
            return   ResponseEntity.ok(brandList);
        }catch (Exception e){
            log.info(SysException.BRAND_QUERY_BY_CID_EXCEPTION.getMsg()+"-->"+ e.getMessage());
        }
        return  ResponseEntity.badRequest().build();
    }

}
