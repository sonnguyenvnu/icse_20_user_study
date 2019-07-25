public void apply(@NotNull SModel model,@NotNull NodeCopier nodeCopier){
  if (isDelete()) {
    ((SModelBase)model).removeEngagedOnGenerationLanguage(myLanguage);
  }
 else {
    ((SModelBase)model).addEngagedOnGenerationLanguage(myLanguage);
  }
}
