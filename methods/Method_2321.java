@Override public List<Record> selectByExampleWithBLOBs(Example example){
  try {
    DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
    Method selectByExampleWithBLOBs=mapper.getClass().getDeclaredMethod("selectByExampleWithBLOBs",example.getClass());
    Object result=selectByExampleWithBLOBs.invoke(mapper,example);
    return (List<Record>)result;
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
