static TypeSpec generatePreviousRenderDataContainerImpl(SpecModel specModel){
  final String className=RenderDataGenerator.getRenderDataImplClassName(specModel);
  final TypeSpec.Builder renderInfoClassBuilder=TypeSpec.classBuilder(className).addSuperinterface(ClassNames.RENDER_DATA);
  if (!specModel.hasInjectedDependencies()) {
    renderInfoClassBuilder.addModifiers(Modifier.STATIC,Modifier.PRIVATE);
    renderInfoClassBuilder.addTypeVariables(specModel.getTypeVariables());
  }
  final String copyParamName="info";
  final String recordParamName="component";
  final MethodSpec.Builder copyBuilder=MethodSpec.methodBuilder("copy").addParameter(ClassName.bestGuess(className),copyParamName);
  final MethodSpec.Builder recordBuilder=MethodSpec.methodBuilder("record").addParameter(specModel.getComponentTypeName(),recordParamName);
  for (  RenderDataDiffModel diff : specModel.getRenderDataDiffs()) {
    final MethodParamModel modelToDiff=SpecModelUtils.getReferencedParamModelForDiff(specModel,diff);
    if (modelToDiff == null) {
      throw new RuntimeException("Got Diff of a param that doesn't seem to exist: " + diff.getName() + ". This should " + "have been caught in the validation pass.");
    }
    if (!(modelToDiff instanceof PropModel || modelToDiff instanceof StateParamModel)) {
      throw new RuntimeException("Got Diff of a param that is not a @Prop or @State! (" + diff.getName() + ", a " + modelToDiff.getClass().getSimpleName() + "). This should have been caught in the " + "validation pass.");
    }
    final String name=modelToDiff.getName();
    if (modelToDiff instanceof PropModel) {
      renderInfoClassBuilder.addField(FieldSpec.builder(modelToDiff.getTypeName(),name).addAnnotation(Prop.class).build());
    }
 else {
      renderInfoClassBuilder.addField(FieldSpec.builder(modelToDiff.getTypeName(),modelToDiff.getName()).addAnnotation(State.class).build());
    }
    copyBuilder.addStatement("$L = $L.$L",name,copyParamName,name);
    recordBuilder.addStatement("$L = $L.$L",name,recordParamName,getImplAccessor(specModel,modelToDiff));
  }
  return renderInfoClassBuilder.addMethod(copyBuilder.build()).addMethod(recordBuilder.build()).build();
}
