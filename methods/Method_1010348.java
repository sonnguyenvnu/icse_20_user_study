@Nullable public ImportElement find(SModelReference modelReference){
  for (  ImportElement importElement : myImplicitImports) {
    if (importElement.getModelReference().equals(modelReference)) {
      return importElement;
    }
  }
  return null;
}
