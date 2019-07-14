@Override public Record selectByPrimaryKey(Integer id){
  try {
    DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
    Method selectByPrimaryKey=mapper.getClass().getDeclaredMethod("selectByPrimaryKey",id.getClass());
    Object result=selectByPrimaryKey.invoke(mapper,id);
    return (Record)result;
  }
 catch (  IllegalAccessException e) {
    e.printStackTrace();
  }
catch (  InvocationTargetException e) {
    e.printStackTrace();
  }
catch (  NoSuchMethodException e) {
    e.printStackTrace();
  }
  DynamicDataSource.clearDataSource();
  return null;
}
