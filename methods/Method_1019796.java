public Object invoke(){
  try {
    return method.invoke(target,args);
  }
 catch (  Exception e) {
    throw new RuntimeException(e);
  }
}
