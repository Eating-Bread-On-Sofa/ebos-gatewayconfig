package cn.edu.bjtu.ebosgatewayconfig.dao;

import cn.edu.bjtu.ebosgatewayconfig.entity.Deviceservice;
import cn.edu.bjtu.ebosgatewayconfig.entity.Rule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RuleRepository extends MongoRepository<Rule,String> {
    public Rule findRuleByName(String name);
    @Override
    public Page<Rule> findAll(Pageable pageable);
    void deleteByRuleName(String name);
    @Override
    List<Rule> findAll();

}
