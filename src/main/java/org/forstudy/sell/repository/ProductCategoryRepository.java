package org.forstudy.sell.repository;

import org.forstudy.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> list);
    List<ProductCategory> findByCategoryNameIn(List<String> list);
}
