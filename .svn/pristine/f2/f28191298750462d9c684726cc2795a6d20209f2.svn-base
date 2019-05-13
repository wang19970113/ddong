package com.ddong.item.service;

import com.ddong.common.pojo.PageResult;
import com.ddong.item.mapper.*;
import com.ddong.item.pojo.*;
import com.ddong.item.vo.SkuVo;
import com.ddong.item.vo.SpuVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private SpuDetailMapper spuDetailMapper;
    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private StockMapper stockMapper;

    @Override
    public PageResult pageList(Long pageIndex, Long pageSize, String key, String sortBy, Boolean desc, Integer saleable) {
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("title", "%" + key + "%");
        }
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + (desc ? " desc" : " asc"));
        }
        PageHelper.startPage(pageIndex.intValue(), pageSize.intValue());
        Page<Spu> spus = (Page<Spu>) goodsMapper.selectByExample(example);
        PageResult pageResult = new PageResult();

        List<Spu> list = spus.getResult();
        List<SpuVo> spuVoList = list.stream().map(spu -> {
            SpuVo spuVo = new SpuVo();
            BeanUtils.copyProperties(spu, spuVo);
            Brand brand = brandMapper.selectByPrimaryKey(spu.getBrandId());
            spuVo.setBrandName(brand.getName());
            List<Long> ids = Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3());
            List<Category> categories = categoryMapper.selectByIdList(ids);
            Object[] objects = categories.stream().map(Category::getName).toArray();
            String categoryName = StringUtils.join(objects, "/");//ddd/fff/bbb
            spuVo.setCategoryName(categoryName);

            return spuVo;
        }).collect(Collectors.toList());
        pageResult.setList(spuVoList);
        pageResult.setPageIndex(pageIndex);
        pageResult.setPageSize(pageSize);
        pageResult.setTotalPages(Long.parseLong(spus.getPages() + ""));
        pageResult.setTotalRows(spus.getTotal());
        return pageResult;
    }

    @Override
    public void delete(Long id) {
        goodsMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional  //事务
    public void save(SpuVo spu) {
        spu.setSaleable(1);
        spu.setValid(1);
        spu.setCreateTime(new Date());
        spu.setLastUpdateTime(spu.getCreateTime());
        spuMapper.insert(spu);

        Long spuId = spu.getId();
        SpuDetail spuDetail = spu.getSpuDetail();
        spuDetail.setSpuId(spuId);
        spuDetailMapper.insert(spuDetail);

        List<SkuVo> skus = spu.getSkus();
        for (SkuVo sku : skus) {
            sku.setSpuId(spuId);
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(sku.getCreateTime());
            skuMapper.insert(sku);
            Long stock = sku.getStock();
            Stock s = new Stock();
            s.setSkuId(sku.getId());
            s.setStock(stock.intValue());
            stockMapper.insert(s);
        }
    }

    @Override
    @Transactional
    public void update(SpuVo spu) {
        spu.setSaleable(1);
        spu.setValid(1);
        spu.setCreateTime(new Date());
        spu.setLastUpdateTime(spu.getCreateTime());
        spuMapper.updateByPrimaryKeySelective(spu);

        Long spuId = spu.getId();
        SpuDetail spuDetail = spu.getSpuDetail();
        spuDetail.setSpuId(spuId);
        spuDetailMapper.updateByPrimaryKeySelective(spuDetail);

        List<SkuVo> skus = spu.getSkus();
        for (SkuVo sku : skus) {
            sku.setSpuId(spuId);
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(sku.getCreateTime());
            skuMapper.updateByPrimaryKeySelective(sku);
            Long stock = sku.getStock();
            Stock s = new Stock();
            s.setSkuId(sku.getId());
            s.setStock(stock.intValue());
            stockMapper.updateByPrimaryKeySelective(s);
        }
    }
}
