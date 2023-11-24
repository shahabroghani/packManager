package com.example.packmanager.repository;

import com.example.packmanager.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT Coalesce(AVG(p.price), 0) FROM Product p where p.weight BETWEEN :minWeight AND :maxWeight ")
    BigDecimal getAverageByWeightBetween(@Param("minWeight") BigDecimal minWeight, @Param("maxWeight") BigDecimal maxWeight);

    List<Product> getAllByIdInAndWeightIsLessThanEqual(List<Long> ids, BigDecimal maxWeight);

}
