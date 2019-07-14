/** 
 * Fills the latest comments.
 * @param dataModel the specified data model
 */
public void fillLatestCmts(final Map<String,Object> dataModel){
  Stopwatchs.start("Fills latest comments");
  try {
    dataModel.put(Common.SIDE_LATEST_CMTS,Collections.emptyList());
  }
  finally {
    Stopwatchs.end();
  }
}
