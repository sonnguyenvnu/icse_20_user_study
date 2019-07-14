@Override public List<Record> selectByExampleForOffsetPage(Example example,Integer offset,Integer limit){
  try {
    DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
    Method selectByExample=mapper.getClass().getDeclaredMethod("selectByExample",example.getClass());
    PageHelper.offsetPage(offset,limit,false);
    Object result=selectByExample.invoke(mapper,example);
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
