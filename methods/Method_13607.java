@ReadOperation public Map<String,Object> invoke(){
  final Map<String,Object> result=new HashMap<>();
  if (sentinelProperties.isEnabled()) {
    result.put("appName",AppNameUtil.getAppName());
    result.put("logDir",LogBase.getLogBaseDir());
    result.put("logUsePid",LogBase.isLogNameUsePid());
    result.put("blockPage",WebServletConfig.getBlockPage());
    result.put("metricsFileSize",SentinelConfig.singleMetricFileSize());
    result.put("metricsFileCharset",SentinelConfig.charset());
    result.put("totalMetricsFileCount",SentinelConfig.totalMetricFileCount());
    result.put("consoleServer",TransportConfig.getConsoleServer());
    result.put("clientIp",TransportConfig.getHeartbeatClientIp());
    result.put("heartbeatIntervalMs",TransportConfig.getHeartbeatIntervalMs());
    result.put("clientPort",TransportConfig.getPort());
    result.put("coldFactor",sentinelProperties.getFlow().getColdFactor());
    result.put("filter",sentinelProperties.getFilter());
    result.put("datasource",sentinelProperties.getDatasource());
    final Map<String,Object> rules=new HashMap<>();
    result.put("rules",rules);
    rules.put("flowRules",FlowRuleManager.getRules());
    rules.put("degradeRules",DegradeRuleManager.getRules());
    rules.put("systemRules",SystemRuleManager.getRules());
    rules.put("authorityRule",AuthorityRuleManager.getRules());
    rules.put("paramFlowRule",ParamFlowRuleManager.getRules());
  }
  return result;
}
