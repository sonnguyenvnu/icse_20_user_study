@Override public void apply(@NotNull SModel model,@NotNull NodeCopier nodeCopier){
  if (isDelete()) {
    new ModelImports(model).removeModelImport(myModelReference);
  }
 else {
    new ModelImports(model).addModelImport(myModelReference);
  }
}
