@Override public Record selectFirstByExample(Example example){
  try {
    DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
    Method selectByExample=mapper.getClass().getDeclaredMethod("selectByExample",example.getClass());
    List<Record> result=(List<Record>)selectByExample.invoke(mapper,example);
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
