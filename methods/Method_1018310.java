/** 
 * clone
 * @param parameter
 * @return
 */
public static Parameter clone(Parameter parameter){
  Parameter dest=new Parameter(parameter.getType(),parameter.getName(),parameter.isVarargs());
  for (  String annotation : parameter.getAnnotations()) {
    dest.addAnnotation(annotation);
  }
  return dest;
}
