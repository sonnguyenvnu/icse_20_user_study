public static TypeSpecDataHolder generateProps(SpecModel specModel){
  final TypeSpecDataHolder.Builder typeSpecDataHolder=TypeSpecDataHolder.newBuilder();
  final ImmutableList<PropModel> props=specModel.getProps();
  boolean hasDynamicProps=false;
  for (  PropModel prop : props) {
    final TypeName propTypeName=prop.getTypeName();
    final TypeName fieldTypeName=!prop.isDynamic() ? propTypeName : ParameterizedTypeName.get(ClassNames.DYNAMIC_VALUE,propTypeName.box());
    final FieldSpec.Builder fieldBuilder=FieldSpec.builder(KotlinSpecUtils.getFieldTypeName(specModel,fieldTypeName),prop.getName()).addAnnotations(prop.getExternalAnnotations()).addAnnotation(AnnotationSpec.builder(Prop.class).addMember("resType","$T.$L",ResType.class,prop.getResType()).addMember("optional","$L",prop.isOptional()).build()).addAnnotation(AnnotationSpec.builder(Comparable.class).addMember("type","$L",getComparableType(fieldTypeName,prop.getTypeSpec())).build());
    if (prop.hasDefault(specModel.getPropDefaults())) {
      assignInitializer(fieldBuilder,specModel,prop);
    }
    typeSpecDataHolder.addField(fieldBuilder.build());
    if (prop.isDynamic()) {
      hasDynamicProps=true;
    }
  }
  if (hasDynamicProps) {
    typeSpecDataHolder.addField(FieldSpec.builder(ArrayTypeName.of(ClassNames.DYNAMIC_VALUE),DYNAMIC_PROPS).addModifiers(Modifier.PRIVATE).build());
  }
  return typeSpecDataHolder.build();
}
