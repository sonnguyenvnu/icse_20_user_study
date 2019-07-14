private static void assignInitializer(FieldSpec.Builder fieldBuilder,SpecModel specModel,PropModel prop){
  if (specModel.getSpecElementType() == SpecElementType.KOTLIN_SINGLETON) {
    final String propName=prop.getName();
    final String propAccessor="get" + propName.substring(0,1).toUpperCase() + propName.substring(1) + "()";
    fieldBuilder.initializer("$L.$L.$L",specModel.getSpecName(),"INSTANCE",propAccessor);
  }
 else {
    fieldBuilder.initializer("$L.$L",specModel.getSpecName(),prop.getName());
  }
}
