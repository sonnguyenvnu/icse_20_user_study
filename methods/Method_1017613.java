/** 
 * @return the database name of the URL
 */
private static String database(Properties props){
  return props.getProperty("PGDBNAME","");
}
