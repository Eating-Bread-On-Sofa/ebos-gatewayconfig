package cn.edu.bjtu.ebosgatewayconfig.dao;

import cn.edu.bjtu.ebosgatewayconfig.entity.Rule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuleRepository extends MongoRepository<Rule,String> {
    public Rule findRuleByGwname(String name);
    @Override
    public Page<Rule> findAll(Pageable pageable);
    void deleteRuleByGwname(String name);
    @Override
    List<Rule> findAll();

}
