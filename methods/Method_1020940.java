protected void merge(Object dest,Object origin){
  try {
    BeanUtils.copyProperties(dest,origin);
  }
 catch (  IllegalAccessException e) {
    e.printStackTrace();
  }
catch (  InvocationTargetException e) {
    e.printStackTrace();
  }
}
