package com.example.packmanager.service.impls;

import com.example.packmanager.dto.ProductDTO;
import com.example.packmanager.dto.SaveProductRequestDTO;
import com.example.packmanager.entity.Product;
import com.example.packmanager.repository.ProductRepository;
import com.example.packmanager.service.interfaces.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository repository;


    /**
     * Method for saving a collection of products in database.
     *
     * @author Shahab Roghani
     *
     * @param request {@link SaveProductRequestDTO} a list of {@link ProductDTO}.
     *
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
     * @author Shahab Roghani
     *
     * @param minWeight minimum of weight in {@link BigDecimal}.
     * @param maxWeight maximum of weight in {@link BigDecimal}.
     *
     * @return the average of products weight in {@link BigDecimal}
     */
    @Override
    public BigDecimal getAvgPrice(BigDecimal minWeight, BigDecimal maxWeight) {
        return repository.getAverageByWeightBetween(minWeight, maxWeight);
    }

    /**
     * Method for getting the best selection of products with maximum profit and minimum weight of given product IDs.
     *
     * @author Shahab Roghani
     *
     * @param maxWeight maximum of pack weight in {@link BigDecimal}.
     * @param ids {@link List} of product IDs in {@link Long}.
     *
     * @return the best collection of {@link Product} as {@link List} of {@link ProductDTO}
     */
    @Override
    public List<ProductDTO> getProperPack(BigDecimal maxWeight, List<Long> ids) {
        List<Product> products = repository.getAllByIdInAndWeightIsLessThanEqualOrderByPriceDescWeightAsc(ids, maxWeight);

        return pack(products, maxWeight).stream().map(product -> new ProductDTO(product.getId(), product.getWeight(), product.getPrice())).toList();
    }

    private List<Product> pack(List<Product> products, BigDecimal maxWeight) {
        ArrayList<Product> bestSelection = new ArrayList<>();

        BigDecimal bestPrice = BigDecimal.ZERO;
        BigDecimal currentPrice = BigDecimal.ZERO;

        BigDecimal bestWeight = BigDecimal.ZERO;
        BigDecimal currentWeight = BigDecimal.ZERO;

        ArrayList<Product> currentSelection = new ArrayList<>();

        for (int index = 0; index < products.size(); index++) {
            if ((currentPrice.compareTo(bestPrice) > 0 ||
                    (currentPrice.compareTo(bestPrice) == 0 && currentWeight.compareTo(bestWeight) < 0)) &&
                    !currentSelection.isEmpty()) {
                bestSelection = (ArrayList<Product>) currentSelection.clone();
                bestPrice = currentPrice;
                bestWeight = currentWeight;
            }
            currentSelection = new ArrayList<>();
            Product first = products.get(index);
            currentSelection.add(first);
            currentPrice = first.getPrice();
            currentWeight = first.getWeight();
            for (int i = index + 1; i < products.size(); i++) {
                Product second = products.get(i);
                if (currentPrice.add(second.getPrice()).compareTo(bestPrice) >= 0
                        && currentWeight.add(second.getWeight()).compareTo(maxWeight) <= 0) {
                    currentSelection.add(second);
                    currentPrice = currentPrice.add(second.getPrice());
                    currentWeight = currentWeight.add(second.getWeight());
                }
            }

        }
        return bestSelection;
    }

}
