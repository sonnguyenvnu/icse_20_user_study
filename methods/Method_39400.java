/** 
 * Builds default method references.
 */
private BeanReferences[] buildDefaultReferences(final Executable methodOrCtor){
  final boolean useParamo=petiteConfig.getUseParamo();
  final PetiteReferenceType[] lookupReferences=petiteConfig.getLookupReferences();
  MethodParameter[] methodParameters=null;
  if (useParamo) {
    methodParameters=Paramo.resolveParameters(methodOrCtor);
  }
  final Class[] paramTypes=methodOrCtor.getParameterTypes();
  final BeanReferences[] references=new BeanReferences[paramTypes.length];
  for (int j=0; j < paramTypes.length; j++) {
    String[] ref=new String[lookupReferences.length];
    references[j]=BeanReferences.of(ref);
    for (int i=0; i < ref.length; i++) {
switch (lookupReferences[i]) {
case NAME:
        ref[i]=methodParameters != null ? methodParameters[j].getName() : null;
      break;
case TYPE_SHORT_NAME:
    ref[i]=StringUtil.uncapitalize(paramTypes[j].getSimpleName());
  break;
case TYPE_FULL_NAME:
ref[i]=paramTypes[j].getName();
break;
}
}
}
return references;
}
