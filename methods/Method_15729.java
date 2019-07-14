protected void setObjectPropertyNull(Object obj,Set<String> fields){
  if (null == obj) {
    return;
  }
  for (  String field : fields) {
    try {
      BeanUtilsBean.getInstance().getPropertyUtils().setProperty(obj,field,null);
    }
 catch (    Exception ignore) {
    }
  }
}
