public void entering(String signature,String methodName){
  methodNames.put(signature,methodName);
  if (called.contains(signature) == false) {
    called.add(signature);
  }
}
