public Object resolveReference(String ref){
  if (contextArray == null) {
    return null;
  }
  for (int i=0; i < contextArray.length && i < contextArrayIndex; i++) {
    ParseContext context=contextArray[i];
    if (context.toString().equals(ref)) {
      return context.object;
    }
  }
  return null;
}
