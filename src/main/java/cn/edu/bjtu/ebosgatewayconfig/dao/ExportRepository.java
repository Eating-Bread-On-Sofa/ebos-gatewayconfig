package cn.edu.bjtu.ebosgatewayconfig.dao;

import cn.edu.bjtu.ebosgatewayconfig.entity.Deviceservice;
import cn.edu.bjtu.ebosgatewayconfig.entity.Export;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExportRepository extends MongoRepository<Export,String> {
    public Export findExportByGwname(String name);
    @Override
    public Page<Export> findAll(Pageable pageable);
    void deleteExportByGwname(String name);
    @Override
    List<Export> findAll();
    Export findExportByGwnameAndVersuon(String name,String version);
}
