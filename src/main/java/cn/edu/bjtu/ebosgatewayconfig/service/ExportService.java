package cn.edu.bjtu.ebosgatewayconfig.service;

import cn.edu.bjtu.ebosgatewayconfig.entity.Deviceprofile;
import cn.edu.bjtu.ebosgatewayconfig.entity.Export;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExportService {
    public boolean addExport(Export export);
    public Export findExportByName(String name);
    public Page<Export> findAllExport(Pageable pageable);
    boolean deleteByExportName(String name);
    public List<Export> findAllExport();
    public void changeExportStatus(Export export);
}
