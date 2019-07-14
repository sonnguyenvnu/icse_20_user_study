/** 
 * Uses both  {@link Types#erasure} and string manipulation to strip any generic types. 
 */
private String doubleErasure(TypeMirror elementType){
  String name=typeUtils.erasure(elementType).toString();
  int typeParamStart=name.indexOf('<');
  if (typeParamStart != -1) {
    name=name.substring(0,typeParamStart);
  }
  return name;
}
