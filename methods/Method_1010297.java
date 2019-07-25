@NotNull public ModelCreationOptions calculate(@NotNull IFile modelFile,@NotNull SourceRoot sourceRoot){
  String modelName=new ModelNameCalculator(myModelRoot,sourceRoot,modelFile).calcModelFqName();
  return calculate(new SModelName(modelName));
}
