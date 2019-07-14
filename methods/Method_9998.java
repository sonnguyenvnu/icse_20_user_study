/** 
 * Fills side hot articles.
 * @param dataModel the specified data model
 */
public void fillSideHotArticles(final Map<String,Object> dataModel){
  Stopwatchs.start("Fills hot articles");
  try {
    dataModel.put(Common.SIDE_HOT_ARTICLES,articleQueryService.getSideHotArticles());
  }
  finally {
    Stopwatchs.end();
  }
}
