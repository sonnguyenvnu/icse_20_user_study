/** 
 * Override hasAttachDetachCallback() method and return true. 
 */
private static MethodSpec generateHasAttachDetachCallback(){
  final MethodSpec.Builder methodSpec=MethodSpec.methodBuilder("hasAttachDetachCallback").addModifiers(Modifier.PROTECTED).addAnnotation(Override.class).returns(TypeName.BOOLEAN);
  methodSpec.addStatement("return true");
  return methodSpec.build();
}
