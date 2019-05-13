package com.ddong.item.mapper;

import com.ddong.item.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BrandMapper extends Mapper<Brand> {
    @Insert("insert into tb_category_brand values(#{cid},#{bid})")
    void insertCategoryBrandMapping(@Param("cid") long cid,@Param("bid") Long id);

   // @Select("select category_id from tb_category_brand where brand_id =#{id}")
  //  List<Long> getCtIdById(Long id);
    @Select("select c.*,cb.category_id from tb_brand c LEFT JOIN tb_category_brand cb ON c.id =cb.brand_id where cb.category_id=#{cid}")
    List<Brand> selectByCid(Long cid);
}
