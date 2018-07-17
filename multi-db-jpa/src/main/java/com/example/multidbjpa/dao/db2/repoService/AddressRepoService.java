package com.example.multidbjpa.dao.db2.repoService;

import com.example.multidbjpa.dao.db2.entity.Address;
import com.example.multidbjpa.dao.db2.repository.AddressRepository;
import com.example.multidbjpa.dao.shared.repoService.BaseRepoService;
import com.example.multidbjpa.dao.shared.repository.BaseRepository;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@CommonsLog
public class AddressRepoService extends BaseRepoService<Address, Long> {


    @Autowired
    AddressRepository addressRepository;

    @Override
    protected BaseRepository<Address, Long> getRepository() {
        return addressRepository;
    }

}
