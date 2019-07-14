private Description getDescriptionForType(TypeCastTree tree,String baseType){
  String targetType=tree.getType().toString();
  return buildDescription(tree).setMessage(String.format("When serialized in Bundle, %s may be transformed into an arbitrary subclass of %s." + " Please cast to %s.",targetType,baseType,baseType)).build();
}
