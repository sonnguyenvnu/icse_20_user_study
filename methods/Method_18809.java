/** 
 * Generate all delegates defined on this  {@link SpecModel}. 
 */
public static TypeSpecDataHolder generateDelegates(SpecModel specModel,Map<Class<? extends Annotation>,DelegateMethodDescription> delegateMethodsMap,EnumSet<RunMode> runMode){
  TypeSpecDataHolder.Builder typeSpecDataHolder=TypeSpecDataHolder.newBuilder();
  boolean hasAttachDetachCallback=false;
  for (  SpecMethodModel<DelegateMethod,Void> delegateMethodModel : specModel.getDelegateMethods()) {
    for (    Annotation annotation : delegateMethodModel.annotations) {
      final Class<? extends Annotation> annotationType=annotation.annotationType();
      if (annotationType.equals(OnAttached.class) || annotationType.equals(OnDetached.class)) {
        hasAttachDetachCallback=true;
      }
      if (delegateMethodsMap.containsKey(annotation.annotationType())) {
        final DelegateMethodDescription delegateMethodDescription=delegateMethodsMap.get(annotation.annotationType());
        typeSpecDataHolder.addMethod(generateDelegate(specModel,delegateMethodDescription,delegateMethodModel,runMode));
        for (        MethodSpec methodSpec : delegateMethodDescription.extraMethods) {
          typeSpecDataHolder.addMethod(methodSpec);
        }
        break;
      }
    }
  }
  if (hasAttachDetachCallback) {
    typeSpecDataHolder.addMethod(generateHasAttachDetachCallback());
  }
  return typeSpecDataHolder.build();
}
