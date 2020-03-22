package com.marcosbarbero.so.multiple.ds.repository.item;

import com.marcosbarbero.so.multiple.ds.entity.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
