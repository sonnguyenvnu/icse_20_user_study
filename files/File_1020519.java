package com.cheikh.lazywaimai.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.cheikh.lazywaimai.model.bean.Product;
import com.cheikh.lazywaimai.model.bean.ProductCategory;
import com.cheikh.lazywaimai.model.bean.ShoppingEntity;
import com.cheikh.lazywaimai.model.event.ShoppingCartChangeEvent;
import com.cheikh.lazywaimai.util.EventUtil;

/**
 * author：cheikh on 16/5/9 14:55
 * email：wanghonghi@126.com
 * 购物车�?�例类
 */
public class ShoppingCart {

    private String mBusinessId;
    private Map<String, ShoppingEntity> mShoppingList;

    private static ShoppingCart instance;

    public static ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }

        return instance;
    }

    private ShoppingCart() {
        mShoppingList = new HashMap<>();
    }

    private void sendChangeEvent() {
        EventUtil.sendEvent(new ShoppingCartChangeEvent());
    }

    /**
     * 往购物车内添加商�?
     * @param product 添加的商�?对象
     * @return 是�?�添加�?功
     */
    public boolean push(Product product) {
        String id = product.getId();
        if (mShoppingList.isEmpty()) {
            // 第一次添加需�?记录商家ID
            mBusinessId = product.getBusinessId();
            // 通过Product对象�?始化一个ShoppingEntity对象
            ShoppingEntity entity = ShoppingEntity.initWithProduct(product);
            mShoppingList.put(id, entity);
            sendChangeEvent();

            return true;
        } else if (mBusinessId.equals(product.getBusinessId())) {
            ShoppingEntity entity = mShoppingList.containsKey(id) ? mShoppingList.get(id) : null;
            if (entity == null) {
                entity = ShoppingEntity.initWithProduct(product);
            } else {
                entity.setQuantity(entity.getQuantity() + 1);
            }
            mShoppingList.put(id, entity);
            sendChangeEvent();

            return true;
        }

        return false;
    }

    /**
     * 往购物车里�?少商�?
     * @param product 需�?�?少的商�?对象
     * @return 是�?��?少�?功
     */
    public boolean pop(Product product) {
        String id = product.getId();
        if (mShoppingList.containsKey(id)) {
            ShoppingEntity entity = mShoppingList.get(id);
            int originQuantity = entity.getQuantity();
            if (originQuantity > 1) {
                entity.setQuantity(--originQuantity);
                mShoppingList.put(id, entity);
                sendChangeEvent();

                return true;
            } else if (originQuantity == 1) {
                mShoppingList.remove(id);
                sendChangeEvent();

                return true;
            }
        }

        return false;
    }

    /**
     * 往购物车里添加指定数�?的商�?
     * @param product 需�?添加的商�?对象
     * @return 是�?�添加�?功
     */
    public boolean set(Product product, int quantity) {
        String id = product.getId();
        if (mShoppingList.isEmpty()) {
            // 第一次添加需�?记录商家ID
            mBusinessId = product.getBusinessId();
        }

        if (mBusinessId.equals(product.getBusinessId())) {
            ShoppingEntity entity = mShoppingList.containsKey(id) ? mShoppingList.get(id) : null;
            if (entity == null) {
                entity = ShoppingEntity.initWithProduct(product);
            }
            if (quantity > 0) {
                entity.setQuantity(quantity);
                mShoppingList.put(id, entity);
            } else {
                mShoppingList.remove(id);
            }
            sendChangeEvent();

            return true;
        }

        return false;
    }

    /**
     * �?�?�一�?�
     * @param shoppingEntities
     */
    public void again(List<ShoppingEntity> shoppingEntities) {
        mShoppingList.clear();
        for (ShoppingEntity entity : shoppingEntities) {
            Product product = entity.getProduct();
            if (product != null) {
                mBusinessId = product.getBusinessId();
                mShoppingList.put(product.getId(), entity);
            }
        }
        sendChangeEvent();
    }

    /**
     * 清空购物车里的所有数�?�
     */
    public void clearAll() {
        mShoppingList.clear();
        sendChangeEvent();
    }

    /**
     * 获�?�商家ID
     * @return 商家ID
     */
    public String getBusinessId() {
        return mBusinessId;
    }

    /**
     * 获�?�购物车里所有商�?的总价
     * @return 商�?总价
     */
    public double getTotalPrice() {
        double totalPrice = 0.0d;
        for (ShoppingEntity entry : mShoppingList.values()) {
            totalPrice += entry.getTotalPrice();
        }

        return totalPrice;
    }

    /**
     * 获�?�购物车里所有商�?的数�?
     * @return 商�?数�?
     */
    public int getTotalQuantity() {
        int totalQuantity = 0;
        for (ShoppingEntity entry : mShoppingList.values()) {
            totalQuantity += entry.getQuantity();
        }

        return totalQuantity;
    }

    /**
     * 获�?�购物车里指定商�?分类的数�?
     * @param category 指定的商�?分类
     * @return 商�?数�?
     */
    public int getQuantityForCategory(ProductCategory category) {
        int totalQuantity = 0;
        for (ShoppingEntity entry : mShoppingList.values()) {
            Product product = entry.getProduct();
            if (product != null && product.getCategoryId().equals(category.getId())) {
                totalQuantity += entry.getQuantity();
            }
        }

        return totalQuantity;
    }

    /**
     * 获�?�购物车里指定商�?的数�?
     * @param product 指定的商�?
     * @return 商�?数�?
     */
    public int getQuantityForProduct(Product product) {
        String id = product.getId();
        if (mShoppingList.containsKey(id)) {
            return mShoppingList.get(id).getQuantity();
        }

        return 0;
    }

    /**
     * 获�?�购物车的选购列表
     * @return 选购列表
     */
    public List<ShoppingEntity> getShoppingList() {
        List<ShoppingEntity> entities = new ArrayList<>();
        for (ShoppingEntity entry : mShoppingList.values()) {
            entities.add(entry);
        }

        return entities;
    }
}
