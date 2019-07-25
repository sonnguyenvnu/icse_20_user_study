public ValueMapping reverse(){
  ValueMapping result;
  if (!MappingConstantsPrism.ANY_REMAINING.equals(source) || !MappingConstantsPrism.ANY_UNMAPPED.equals(source)) {
    result=new ValueMapping(target,source,mirror,sourceAnnotationValue,targetAnnotationValue);
  }
 else {
    result=null;
  }
  return result;
}
