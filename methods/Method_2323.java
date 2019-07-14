@Override public List<Record> selectByExampleWithBLOBsForStartPage(Example example,Integer pageNum,Integer pageSize){
  try {
    DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
    Method selectByExampleWithBLOBs=mapper.getClass().getDeclaredMethod("selectByExampleWithBLOBs",example.getClass());
    PageHelper.startPage(pageNum,pageSize,false);
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
