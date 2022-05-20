package com.vsk.demo.service;

import com.vsk.demo.entities.ShopItems;

import java.util.List;

public interface ItemService {

    ShopItems addItem(ShopItems items);
    List<ShopItems> getAllItems();
    ShopItems getItem(Long id);
    void deleteItem(ShopItems item);
    ShopItems saveItem(ShopItems item);

}
