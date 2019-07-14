public Integer readInteger(){
  Object object;
  if (context == null) {
    object=parser.parse();
  }
 else {
    readBefore();
    object=parser.parse();
    readAfter();
  }
  return TypeUtils.castToInt(object);
}
