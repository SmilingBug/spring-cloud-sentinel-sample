package com.open.onebyte.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 流控
 *
 * @author yuntai
 */
@Slf4j
@RestController
public class FlowRuleController {

    @SentinelResource(value = "echo", blockHandler = "echoBlockHandler")
    @GetMapping("/echo")
    public String echo() {
        return "echo";
    }

    public String echoBlockHandler(BlockException e) {
        log.error("echoBlockHandler error", e);
        return "blocked!";
    }
}
