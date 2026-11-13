package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public double calculateFinalPrice(double basePrice, double discountPercent, double taxPercent) {
        logger.info("Bắt đầu tính giá đơn hàng: basePrice={}, discount={}%, tax={}%",
                basePrice, discountPercent, taxPercent);

        if (basePrice < 0) {
            logger.error("Giá gốc không hợp lệ: {}", basePrice);
            throw new IllegalArgumentException("Giá gốc không được âm");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            logger.error("Phần trăm giảm giá không hợp lệ: {}", discountPercent);
            throw new IllegalArgumentException("Giảm giá phải nằm trong khoảng 0 - 100%");
        }
        if (taxPercent < 0) {
            logger.error("Thuế VAT không hợp lệ: {}", taxPercent);
            throw new IllegalArgumentException("Thuế không được âm");
        }

        double discountedPrice = basePrice * (1 - discountPercent / 100.0);
        double finalPrice = discountedPrice * (1 + taxPercent / 100.0);

        logger.info("Tính toán hoàn tất. Giá sau giảm: {}, Giá cuối cùng: {}",
                discountedPrice, finalPrice);
        return Math.round(finalPrice * 100.0) / 100.0;
    }

    public static void main(String[] args) {
        OrderService service = new OrderService();
        double total = service.calculateFinalPrice(1000.0, 10.0, 8.0);
        logger.info("Kết quả chạy thử: {}", total);
    }
}