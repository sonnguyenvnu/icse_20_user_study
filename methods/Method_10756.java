/** 
 * ????????? <p>/data/data/com.xxx.xxx/databases/dbName</p>
 * @param dbName ?????
 * @return {@code true}: ????<br> {@code false}: ????
 */
public static boolean cleanInternalDbByName(Context context,String dbName){
  return context.deleteDatabase(dbName);
}
