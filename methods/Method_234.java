static boolean requiresCast(TypeName type){
  return !VIEW_TYPE.equals(type.toString());
}
