/** 
 * @return ?????
 */
public static DynamicDataSource defaultDataSource(){
  checkDynamicDataSourceReady();
  return dynamicDataSourceService.getDefaultDataSource();
}
