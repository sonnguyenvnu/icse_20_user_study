/** 
 * Returns method parameter names.
 */
public String[] resolveParamNames(final Method actionClassMethod){
  MethodParameter[] methodParameters=Paramo.resolveParameters(actionClassMethod);
  String[] names=new String[methodParameters.length];
  for (int i=0; i < methodParameters.length; i++) {
    names[i]=methodParameters[i].getName();
  }
  return names;
}
