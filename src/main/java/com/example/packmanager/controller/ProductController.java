package com.example.packmanager.controller;

import com.example.packmanager.dto.GetAvgPriceResponseDTO;
import com.example.packmanager.dto.GetProperPackResponseDTO;
import com.example.packmanager.dto.ProductDTO;
import com.example.packmanager.dto.SaveProductRequestDTO;
import com.example.packmanager.service.interfaces.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(
        value = "/products"
)
public class ProductController {

    private final IProductService productService;

    @PostMapping("/saveInDB")
    public void saveInDB(@RequestBody SaveProductRequestDTO request) {
        productService.saveAll(request);
    }

    @GetMapping("/avgPrice")
    public ResponseEntity<GetAvgPriceResponseDTO> avgPrice(@RequestParam(name = "minWeight") BigDecimal minWeight, @RequestParam(name = "maxWeight") BigDecimal maxWeight) {
        BigDecimal avgPrice = productService.getAvgPrice(minWeight, maxWeight);
        return ResponseEntity.ok(new GetAvgPriceResponseDTO(avgPrice));
    }

    @GetMapping("/getProperPack")
    public ResponseEntity<GetProperPackResponseDTO> getProperPack(@RequestParam(name = "maxWeight") BigDecimal maxWeight, @RequestParam(name = "ids") List<Long> ids) {
        List<ProductDTO> productDTOS = productService.getProperPack(maxWeight, ids);
        return ResponseEntity.ok(new GetProperPackResponseDTO(productDTOS));
    }


}
