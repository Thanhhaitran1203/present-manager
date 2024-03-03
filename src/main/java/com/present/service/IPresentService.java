package com.present.service;

import com.present.model.Present;

import java.util.List;

public interface IPresentService {
    List<Present> findAll();

    void save(Present present);

    Present findById(int id);

    void remove(int id);
}
