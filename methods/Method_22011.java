/** 
 * ????????????.
 * @param liteJobConfig ????
 */
public void persist(final LiteJobConfiguration liteJobConfig){
  checkConflictJob(liteJobConfig);
  if (!jobNodeStorage.isJobNodeExisted(ConfigurationNode.ROOT) || liteJobConfig.isOverwrite()) {
    jobNodeStorage.replaceJobNode(ConfigurationNode.ROOT,LiteJobConfigurationGsonFactory.toJson(liteJobConfig));
  }
}
