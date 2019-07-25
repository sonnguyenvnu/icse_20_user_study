private boolean accept(Method method){
  return method.name().equals(myMethodName) && method.signature().equals(myJniSignature);
}
