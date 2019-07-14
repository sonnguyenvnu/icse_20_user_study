/** 
 * ??????.
 * @param liteJobConfig ????
 * @return ????????
 */
public LiteJobConfiguration updateJobConfiguration(final LiteJobConfiguration liteJobConfig){
  configService.persist(liteJobConfig);
  return configService.load(false);
}
