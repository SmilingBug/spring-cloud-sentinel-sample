package com.open.onebyte.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Collections;

/**
 * 热点流控
 *
 * @author yuntai
 */
@Slf4j
@RestController
public class ParamFlowRuleController {

    @SentinelResource(value = "paramFlow", blockHandler = "echoBlockHandler")
    @GetMapping("/paramFlow")
    public String echo(String id) {
        return "access" + id;
    }

    public String echoBlockHandler(String id, BlockException e) {
        log.error("echoBlockHandler error", e);
        return id + "blocked!";
    }

    @PostConstruct
    public void initParamFlowRules() {
        ParamFlowRule paramFlowRule = new ParamFlowRule("paramFlow");
        paramFlowRule.setParamIdx(0);
        paramFlowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        paramFlowRule.setCount(1);
        ParamFlowRuleManager.loadRules(Collections.singletonList(paramFlowRule));
    }
}
