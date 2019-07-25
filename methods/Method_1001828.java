public boolean handwritten(){
  final String value=getValue("handwritten");
  if ("true".equalsIgnoreCase(value)) {
    return true;
  }
  return false;
}
