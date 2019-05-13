package com.ddong.item.service;

import com.ddong.item.mapper.CategoryMapper;
import com.ddong.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public List<Category> queryCategoryByPid(Long pid) {
        //根据父id查询数据集合
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId",pid);
        List<Category> list = categoryMapper.selectByExample(example);
        return list;

    }

    @Override
    public Category addCategory(Category category) {
        category.setId(null);
        categoryMapper.insert(category);
        Category pt = categoryMapper.selectByPrimaryKey(category.getParentId());
        if (pt.getIsParent()==0){
            pt.setIsParent(1);
            categoryMapper.updateByPrimaryKey(pt);
        }
        return category;
    }

    @Override
    public void updateCategory(Category category) {
        categoryMapper.updateByPrimaryKeySelective(category);
    }


    @Override
    public void deleteCategory(Long id) {
        List<Long> list=new ArrayList<Long>();
        Category ct1 = categoryMapper.selectByPrimaryKey(id);
        findAllCategoryById(ct1,list);
        for (Long i:list){
            categoryMapper.deleteByPrimaryKey(i);
        }

        Category ct3 = categoryMapper.selectByPrimaryKey(ct1.getParentId());
        List<Category> cate = queryCategoryByPid(ct1.getParentId());
        if(cate !=null && cate.size()>0){
            return;
        }
        ct3.setIsParent(0);
        categoryMapper.updateByPrimaryKey(ct3);

    }

    public void  findAllCategoryById (Category ct1,List<Long> list){

        list.add(ct1.getId());

        if(ct1.getIsParent()==0)return;

        List<Category> ctlist = queryCategoryByPid(ct1.getId());
        for(Category ct2:ctlist){
            findAllCategoryById (ct2, list);
        }
    }
}
