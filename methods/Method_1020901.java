public JPABase attr(String fieldName,Object value){
  try {
    BeanUtils.setProperty(this,fieldName,value);
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return this;
}
