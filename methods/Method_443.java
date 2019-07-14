public Object getObject(String path){
  for (int i=0; i < contextArrayIndex; ++i) {
    if (path.equals(contextArray[i].toString())) {
      return contextArray[i].object;
    }
  }
  return null;
}
