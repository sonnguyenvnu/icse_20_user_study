/** 
 * ????id????????
 * @param id ???id {@link DynamicDataSource#getId()}
 * @return ???????
 */
public static boolean existing(String id){
  try {
    checkDynamicDataSourceReady();
    return dynamicDataSourceService.getDataSource(id) != null;
  }
 catch (  DataSourceNotFoundException e) {
    return false;
  }
}
