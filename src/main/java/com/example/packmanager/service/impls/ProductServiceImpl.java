package com.example.packmanager.service.impls;

import com.example.packmanager.dto.ProductDTO;
import com.example.packmanager.dto.SaveProductRequestDTO;
import com.example.packmanager.entity.Product;
import com.example.packmanager.repository.ProductRepository;
import com.example.packmanager.service.interfaces.IProductService;
import com.example.packmanager.util.PackUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository repository;


    /**
     * Method for saving a collection of products in database.
     *
     * @param request {@link SaveProductRequestDTO} a list of {@link ProductDTO}.
     * @author Shahab Roghani
     */
    @Override
    public void saveAll(SaveProductRequestDTO request) {
        if (request.getProducts() != null && !request.getProducts().isEmpty()) {
            List<Product> products = request.getProducts()
                    .stream()
                    .map(productDTO ->
                            new Product(productDTO.getWeight(), productDTO.getPrice())
                    ).toList();

            repository.saveAll(products);
        }
    }

    /**
     * Method for getting average price of Products in range of Weight.
     *
     * @param minWeight minimum of weight in {@link BigDecimal}.
     * @param maxWeight maximum of weight in {@link BigDecimal}.
     * @return the average of products weight in {@link BigDecimal}
     * @author Shahab Roghani
     */
    @Override
    public BigDecimal getAvgPrice(BigDecimal minWeight, BigDecimal maxWeight) {
        return repository.getAverageByWeightBetween(minWeight, maxWeight);
    }

    /**
     * Method for getting the best selection of products with maximum profit and minimum weight of given product IDs.
     *
     * @param maxWeight maximum of pack weight in {@link BigDecimal}.
     * @param ids       {@link List} of product IDs in {@link Long}.
     * @return the best collection of {@link Product} as {@link List} of {@link ProductDTO}
     * @author Shahab Roghani
     */
    @Override
    public List<ProductDTO> getProperPack(BigDecimal maxWeight, List<Long> ids) {
        List<Product> products = repository.getAllByIdInAndWeightIsLessThanEqual(ids, maxWeight);
        PackUtil packUtil = new PackUtil(products, maxWeight);
        List<Product> pack = packUtil.pack();
        return pack.stream().map(product -> new ProductDTO(product.getId(), product.getWeight(), product.getPrice())).toList();
    }
}
