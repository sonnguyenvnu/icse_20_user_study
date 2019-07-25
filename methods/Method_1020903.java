public boolean merge(Map params){
  try {
    BeanUtils.copyProperties(this,params);
  }
 catch (  Exception e) {
    return false;
  }
  return true;
}
