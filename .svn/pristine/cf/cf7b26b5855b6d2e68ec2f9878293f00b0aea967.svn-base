package com.ddong.item.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "tb_brand")
public class Brand {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String image;
  private Character letter;
@Transient
  private  String categories;
}
