@Override public List<Record> selectByExampleForStartPage(Example example,Integer pageNum,Integer pageSize){
  try {
    DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
    Method selectByExample=mapper.getClass().getDeclaredMethod("selectByExample",example.getClass());
    PageHelper.startPage(pageNum,pageSize,false);
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
