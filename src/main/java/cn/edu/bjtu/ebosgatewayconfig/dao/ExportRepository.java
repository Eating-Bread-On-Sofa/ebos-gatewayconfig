package cn.edu.bjtu.ebosgatewayconfig.dao;

import cn.edu.bjtu.ebosgatewayconfig.entity.Deviceservice;
import cn.edu.bjtu.ebosgatewayconfig.entity.Export;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ExportRepository extends MongoRepository<Export,String> {
    public Export findExportByName(String name);
    @Override
    public Page<Export> findAll(Pageable pageable);
    void deleteByExportName(String name);
    @Override
    List<Export> findAll();
}
