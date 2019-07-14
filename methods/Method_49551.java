static Configuration getJobConfiguration(ModifiableHadoopConfiguration scanConf){
  if (!scanConf.has(JanusGraphHadoopConfiguration.SCAN_JOB_CONFIG_ROOT)) {
    log.debug("No job configuration root provided");
    return null;
  }
  ConfigNamespace jobRoot=getJobRoot(scanConf.get(JanusGraphHadoopConfiguration.SCAN_JOB_CONFIG_ROOT));
  return ModifiableHadoopConfiguration.prefixView(jobRoot,JanusGraphHadoopConfiguration.SCAN_JOB_CONFIG_KEYS,scanConf);
}
