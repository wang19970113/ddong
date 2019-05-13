package com.ddong.item.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tb_spu_detail")
public class SpuDetail {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long spuId;
  private String description;
  private String specifications;
  private String specTemplate;
  private String packingList;
  private String afterService;

}
