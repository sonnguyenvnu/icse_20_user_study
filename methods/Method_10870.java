/** 
 * dao class already define the sql exec method, so just invoke it
 */
private static void reflectMethod(StandardDatabase db,String methodName,boolean isExists,@NonNull Class<? extends AbstractDao<?,?>>... daoClasses){
  if (daoClasses.length < 1) {
    return;
  }
  try {
    for (    Class cls : daoClasses) {
      Method method=cls.getDeclaredMethod(methodName,Database.class,boolean.class);
      method.invoke(null,db,isExists);
    }
  }
 catch (  NoSuchMethodException e) {
    e.printStackTrace();
  }
catch (  InvocationTargetException e) {
    e.printStackTrace();
  }
catch (  IllegalAccessException e) {
    e.printStackTrace();
  }
}
