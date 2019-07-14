private static String positionAsString(Type type,List<Type> types){
  int pos=position(type,types);
  if (pos < 0) {
    return "unknown";
  }
  return String.valueOf(pos);
}
