/** 
 * ??SQL??
 * @param table
 * @param request
 * @param isProcedure 
 * @return
 * @throws Exception 
 */
public static SQLConfig newSQLConfig(RequestMethod method,String table,JSONObject request,List<Join> joinList,boolean isProcedure) throws Exception {
  return newSQLConfig(method,table,request,joinList,isProcedure,SIMPLE_CALLBACK);
}
