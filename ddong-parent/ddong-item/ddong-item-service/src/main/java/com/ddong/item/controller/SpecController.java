package com.ddong.item.controller;

import com.ddong.common.exception.SysException;
import com.ddong.item.pojo.Specification;
import com.ddong.item.service.SpecService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("spec")
@Slf4j
public class SpecController {
    @Autowired
    private SpecService specService;
    @GetMapping("{cid}")
    public ResponseEntity  querySpecByCid(@PathVariable Long cid){
        try {
            Specification spec= specService.querySpecByCid(cid);
            return  ResponseEntity.ok(spec.getSpecifications());
        }catch (Exception e){
log.info(SysException.SPEC_QUERY_BY_CID_EXCPTION+"--->"+e.getMessage());
        }
return  ResponseEntity.badRequest().build();

    }
    @GetMapping("add")
    public  ResponseEntity add(String[] cid ){
        return ResponseEntity.badRequest().build();

    }
}
