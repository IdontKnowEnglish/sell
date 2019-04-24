package com.yu.sell.repository;

import com.yu.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 75839
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

    List<ProductCategory>findByCategoryTypeIn(List<Integer> categoryTypeList);
}
