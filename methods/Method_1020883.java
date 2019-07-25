private JPQL jpql(){
  JPQL jpql=null;
  if (type.equals("javax.persistence.ManyToMany") || type.equals("javax.persistence.ManyToOne")) {
    jpql=(JPQL)ReflectHelper.staticMethod(getTargetModelClass(),"joins",targetField + " as framework_service_holder_" + targetField);
  }
  if (jpql != null) {
    jpql.where(map("framework_service_holder_" + targetField,object));
  }
 else {
    jpql=(JPQL)ReflectHelper.staticMethod(getTargetModelClass(),"where",targetField + "=:framework_service_holder",map("framework_service_holder",object));
  }
  return jpql;
}
