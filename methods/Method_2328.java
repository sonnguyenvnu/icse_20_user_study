@Override public Record selectFirstByExampleWithBLOBs(Example example){
  try {
    DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
    Method selectByExampleWithBLOBs=mapper.getClass().getDeclaredMethod("selectByExampleWithBLOBs",example.getClass());
    List<Record> result=(List<Record>)selectByExampleWithBLOBs.invoke(mapper,example);
    if (null != result && result.size() > 0) {
      return result.get(0);
    }
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
