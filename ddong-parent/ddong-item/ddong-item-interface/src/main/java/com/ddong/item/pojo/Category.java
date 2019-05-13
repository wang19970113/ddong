package com.ddong.item.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name="tb_category")
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private Long parentId;
  private Integer isParent;
  private Integer sort;

  public void setId(Long id) {
    this.id = id;
  }
}
