/** 
 * @return ????????
 */
public static DynamicDataSource currentDataSource(){
  String id=dataSourceSwitcher.currentDataSourceId();
  if (id == null) {
    return defaultDataSource();
  }
  checkDynamicDataSourceReady();
  return dynamicDataSourceService.getDataSource(id);
}
