private String buildDebugInputsString(){
  if (mInputs == null) {
    return "[]";
  }
  String inputNames="";
  final Iterator<String> inputIterator=mInputs.keySet().iterator();
  while (inputIterator.hasNext()) {
    inputNames+="'" + inputIterator.next() + "'";
    if (!inputIterator.hasNext()) {
      inputNames+=", ";
    }
  }
  return "[" + inputNames + "]";
}
