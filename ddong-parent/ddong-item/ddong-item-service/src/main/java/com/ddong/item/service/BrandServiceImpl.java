package com.ddong.item.service;

import com.ddong.common.pojo.PageResult;
import com.ddong.item.mapper.BrandMapper;
import com.ddong.item.pojo.Brand;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;
    @Override
    public PageResult pageList(Long pageIndex, Long pageSize, String key, String sortby, Boolean desc) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(key)){
            criteria.andLike("name","%"+key+"%");
            criteria.orLike("letter","%"+key+"%");
        }
        if(StringUtils.isNotBlank(sortby)){
            example.setOrderByClause(sortby+(desc?" desc" : " asc"));
        }
        PageHelper.startPage(pageIndex.intValue(),pageSize.intValue());
        Page<Brand> brands = (Page<Brand>) brandMapper.selectByExample(example);
         PageResult pageResult=new PageResult();
         pageResult.setList(brands);
         pageResult.setPageIndex(pageIndex);
         pageResult.setPageSize(pageSize);
         pageResult.setTotalPages(Long.parseLong(brands.getPages()+""));
         pageResult.setTotalRows(brands.getTotal());
        return pageResult;
    }

    @Override
    public void add(Brand brand) {
        brandMapper.insert(brand);
        String[] split=brand.getCategories().split(",");
        for (String s:split) {
            brandMapper.insertCategoryBrandMapping(Long.parseLong(s),brand.getId());
        }
    }

    @Override
    public void delete(Long id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public List<Brand> queryByCid(Long cid) {

        return brandMapper.selectByCid(cid);
    }


}
