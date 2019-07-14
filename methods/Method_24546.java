private Method findCallback(final String name){
  try {
    return parent.getClass().getMethod(name,this.getClass());
  }
 catch (  Exception e) {
  }
  try {
    return parent.getClass().getMethod(name,Object.class);
  }
 catch (  Exception e) {
  }
  return null;
}
