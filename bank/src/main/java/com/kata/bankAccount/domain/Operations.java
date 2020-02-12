package com.kata.bankAccount.domain;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Operations {

    private final List<Operation> items = new ArrayList<>();

    public Operations() {

    }

    public Operations(Operation operation) {

        this(Collections.singleton(operation));
    }

    public Operations(Collection<Operation> items) {

        if (CollectionUtils.isNotEmpty(items)) {
            this.items.addAll(items);
        }
    }

    public void add(Operation operation) {

        items.add(operation);
    }

    public List<Operation> getItems() {

        return items;
    }
}
