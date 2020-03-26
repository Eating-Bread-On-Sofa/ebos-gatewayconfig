package cn.edu.bjtu.ebosgatewayconfig.service.iml;

import cn.edu.bjtu.ebosgatewayconfig.dao.RuleRepository;
import cn.edu.bjtu.ebosgatewayconfig.entity.Rule;
import cn.edu.bjtu.ebosgatewayconfig.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleServiceImpl implements RuleService {

    @Autowired
    RuleRepository ruleRepository;
    @Override
    public boolean addRule(Rule rule) {
        Rule rule1 = ruleRepository.findRuleByName(rule.getGwname());
        if (rule1 == null) {
            ruleRepository.save(rule);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Rule findRuleByName(String name) {
        return ruleRepository.findRuleByName(name);
    }

    @Override
    public Page<Rule> findAllRule(Pageable pageable) {
        return ruleRepository.findAll(pageable);
    }

    @Override
    public boolean deleteByRuleName(String name) {
        Rule rule = ruleRepository.findRuleByName(name);
        if (rule == null) {
            return false;
        } else {
            ruleRepository.deleteByRuleName(name);
            return true;
        }
    }

    @Override
    public List<Rule> findAllRule() {
        return ruleRepository.findAll();
    }

    @Override
    public void changeRuleStatus(Rule rule) {
        ruleRepository.save(rule);
    }
}
