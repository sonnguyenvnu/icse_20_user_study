public void trigger(Object obj){
  try {
    method.invoke(obj);
  }
 catch (  Exception e) {
    throw Lang.wrapThrow(e);
  }
}
