package com.iessanalberto.dam1.jdbc.services;


import com.iessanalberto.dam1.jdbc.repositories.FamilyRepositoryBD;

import java.util.ArrayList;

public class FamilyService {

    public ArrayList<String> loadFamilies () throws Exception {
        return FamilyRepositoryBD.getFamilyList();
    }
}
