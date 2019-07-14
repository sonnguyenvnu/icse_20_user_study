/** 
 * @return ????????????
 */
public static boolean currentExisting(){
  if (currentIsDefault()) {
    return true;
  }
  try {
    return currentDataSource() != null;
  }
 catch (  DataSourceNotFoundException e) {
    return false;
  }
}
