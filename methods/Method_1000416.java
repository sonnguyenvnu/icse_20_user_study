private Pojo make(Class<?> clazz){
  Entity<?> en=holder.getEntity(clazz);
  pojo=Pojos.pojo(expert,en,SqlType.SELECT);
  pojo.setEntity(en);
  return pojo;
}
