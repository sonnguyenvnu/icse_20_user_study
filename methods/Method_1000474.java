/** 
 * ???????????????
 * @param dao Dao??
 * @param filter ????
 * @param tableName ??????
 * @return ????Dao??
 */
public static Dao ext(Dao dao,FieldFilter filter,Object tableName){
  if (tableName == null && filter == null)   return dao;
  ExtDaoInvocationHandler handler=new ExtDaoInvocationHandler(dao,filter,tableName);
  return (Dao)Proxy.newProxyInstance(dao.getClass().getClassLoader(),iz,handler);
}
