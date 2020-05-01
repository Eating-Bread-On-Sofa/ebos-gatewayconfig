package cn.edu.bjtu.ebosgatewayconfig.service;

import cn.edu.bjtu.ebosgatewayconfig.entity.Rule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RuleService {
    public boolean addRule(Rule rule);
    public Rule findRuleByName(String name);
    public Page<Rule> findAllRule(Pageable pageable);
    boolean deleteByRuleName(String name);
    public List<Rule> findAllRule();
    public void changeRuleStatus(Rule rule);}
