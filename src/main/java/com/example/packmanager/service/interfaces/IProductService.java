package com.example.packmanager.service.interfaces;

import com.example.packmanager.dto.ProductDTO;
import com.example.packmanager.dto.SaveProductRequestDTO;

import java.math.BigDecimal;
import java.util.List;

public interface IProductService {

    void saveAll(SaveProductRequestDTO request);

    BigDecimal getAvgPrice(BigDecimal minWeight, BigDecimal maxWeight);

    List<ProductDTO> getProperPack(BigDecimal maxWeight, List<Long> ids);
}
