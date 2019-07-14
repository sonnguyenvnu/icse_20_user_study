static TypeSpecDataHolder generateWorkingRangeMethodDelegates(SpecModel specModel){
  final TypeSpecDataHolder.Builder typeSpecDataHolder=TypeSpecDataHolder.newBuilder();
  for (  WorkingRangeMethodModel model : specModel.getWorkingRangeMethods()) {
    if (model.enteredRangeModel != null) {
      typeSpecDataHolder.addMethod(generateWorkingRangeMethodDelegate(specModel,model.enteredRangeModel));
    }
    if (model.exitedRangeModel != null) {
      typeSpecDataHolder.addMethod(generateWorkingRangeMethodDelegate(specModel,model.exitedRangeModel));
    }
  }
  return typeSpecDataHolder.build();
}
