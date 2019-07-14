public String getElement(){
  if (null != element) {
    return SafeEncoder.encode(element);
  }
 else {
    return null;
  }
}
