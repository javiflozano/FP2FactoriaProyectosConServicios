package com.iessanalberto.dam1.jdbc.services;

import com.iessanalberto.dam1.jdbc.models.Institution;
import com.iessanalberto.dam1.jdbc.repositories.InstitutionRepositoryBD;

import java.util.ArrayList;


public class InstitutionService {
    public ArrayList<Institution> loadAllInstitutionsNames() throws Exception
    {return InstitutionRepositoryBD.getInstitutionList();
    }


}
