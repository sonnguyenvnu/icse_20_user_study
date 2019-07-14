private void setIfNonNull(Object value,Element target,String id){
  if (value != null) {
    target.setAttribute(id,value.toString());
  }
}
