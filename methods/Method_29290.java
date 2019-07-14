private void printConfig(List<String> masterConfigs){
  logger.info("==================redis-{}-config==================",masterConfigs);
  for (  String line : masterConfigs) {
    logger.info(line);
  }
}
