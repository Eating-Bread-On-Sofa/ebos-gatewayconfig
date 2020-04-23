package cn.edu.bjtu.ebosgatewayconfig.service.iml;

import cn.edu.bjtu.ebosgatewayconfig.dao.ExportRepository;
import cn.edu.bjtu.ebosgatewayconfig.entity.Export;
import cn.edu.bjtu.ebosgatewayconfig.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.websocket.server.ServerEndpoint;
import java.util.List;

@Service
public class ExportServiceImpl implements ExportService {

    @Autowired
    ExportRepository exportRepository;
    @Override
    public boolean addExport(Export export) {
//        Export export1 = exportRepository.findExportByGwname(export.getGwname());
//        if (export1 == null) {
            exportRepository.save(export);
            return true;
//        } else {
//            return false;
//        }
    }

    @Override
    public Export findExportByName(String name) {
        return exportRepository.findExportByGwname(name);
    }

    @Override
    public Page<Export> findAllExport(Pageable pageable) {
        return exportRepository.findAll(pageable);
    }

    @Override
    public boolean deleteByExportName(String name) {
        Export export = exportRepository.findExportByGwname(name);
        if (export == null) {
            return false;
        } else {
            exportRepository.deleteExportByGwname(name);
            return true;
        }
    }

    @Override
    public List<Export> findAllExport() {
        return exportRepository.findAll();
    }

    @Override
    public void changeExportStatus(Export export) {
        exportRepository.save(export);
    }

    @Override
    public Export findByNameAndVersion(String name, String version) {
        return exportRepository.findExportByGwnameAndVersuon(name, version);
    }
}
