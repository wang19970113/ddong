package com.ddong.item.controller;

import com.ddong.common.exception.SysException;
import com.ddong.common.pojo.PageResult;
import com.ddong.item.service.GoodsService;
import com.ddong.item.vo.SpuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("goods")
public class GoodsController {
    private Logger logger=Logger.getLogger(this.getClass().getName());
    @Autowired
    private GoodsService goodsService;
    @GetMapping("pageList")
    public ResponseEntity pageList(@RequestParam(defaultValue = "1")Long pageIndex, @RequestParam(defaultValue = "5")Long pageSize, String key, String sortBy, Boolean desc,@RequestParam(defaultValue = "1") Integer saleable){
        try {
            PageResult pr= goodsService.pageList(pageIndex,pageSize,key,sortBy,desc,saleable);
            return  ResponseEntity.ok(pr);
        }catch (Exception e){
            logger.info("商品分页失败"+e.getMessage());
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("delete")
    public ResponseEntity delete(@RequestParam(value = "id")Long id){
        try {
            goodsService.delete(id);
            return  ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  ResponseEntity.badRequest().build();
    }
    @PostMapping("save")
    public ResponseEntity save(@RequestBody SpuVo spu){
        try {

            goodsService.update(spu);
            return  ResponseEntity.ok().build();
        }catch (Exception e){
            logger.info(SysException.GOODS_update_EXCEPTION+"-->"+e.getMessage());
        }
        return  ResponseEntity.badRequest().build();
    }
    @PutMapping("save")
    public ResponseEntity update(@RequestBody SpuVo spu){
        try {

            goodsService.save(spu);
            return  ResponseEntity.ok().build();
        }catch (Exception e){
            logger.info(SysException.GOODS_SAVE_EXCEPTION+"-->"+e.getMessage());
        }
        return  ResponseEntity.badRequest().build();
    }

}
