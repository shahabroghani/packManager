package com.example.packmanager.util;

import com.example.packmanager.entity.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PackUtil {

    List<Product> products;
    BigDecimal maxWeight;

    int n;

    Map<Integer, Map<BigDecimal, BigDecimal>> results;

    public PackUtil(List<Product> products, BigDecimal maxWeight) {
        this.products = products;
        this.maxWeight = maxWeight;
        this.n = products.size();
        results = new HashMap<>();
    }

    public List<Product> pack() {
        calculateBestProfit(maxWeight, n);
        return getBestSelectionItems();
    }

    private List<Product> getBestSelectionItems() {
        List<Product> bestSelection = new ArrayList<>();
        BigDecimal remainingWeight = maxWeight;
        for (int i = n; i > 0 && remainingWeight.compareTo(BigDecimal.ZERO) > 0; i--) {
            BigDecimal first = results.get(i).get(remainingWeight);
            BigDecimal second = results.get(i - 1).get(remainingWeight);
            if (first.compareTo(second) > 0) {
                Product product = products.get(i - 1);
                bestSelection.add(product);
                remainingWeight = remainingWeight.subtract(product.getWeight());
            }
        }
        return bestSelection;
    }

    private BigDecimal calculateBestProfit(BigDecimal maxWeight, int index) {
        BigDecimal result;
        if (index == 0 || maxWeight.equals(BigDecimal.ZERO)) {
            result = BigDecimal.ZERO;
        } else {
            Product product = products.get(index - 1);
            if (product.getWeight().compareTo(maxWeight) > 0) {
                result = calculateBestProfit(maxWeight, index - 1);
            } else {
                BigDecimal notIncluded = calculateBestProfit(maxWeight, index - 1);
                BigDecimal included = product.getPrice()
                        .add(calculateBestProfit(maxWeight.subtract(product.getWeight()), index - 1));
                result = included.compareTo(notIncluded) >= 0 ? included : notIncluded;
            }
        }
        Map<BigDecimal, BigDecimal> row = results.getOrDefault(index, new HashMap<>());
        row.put(maxWeight, result);
        results.put(index, row);
        return result;
    }
}
