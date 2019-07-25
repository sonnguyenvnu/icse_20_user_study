@Override public synchronized void changed(DataSource source,final PsiJavaStubEvent event){
  final SModel actualModel=getCurrentModelInternal();
  if (actualModel == null) {
    return;
  }
  final SModel modelCopy=CopyUtil.copyModel(actualModel);
  for (  PsiJavaFile file : event.removed()) {
    myMps2PsiMapper.clearFile(modelCopy,file.getName());
  }
  for (  JavaPsiListener.FSRename rename : event.renamed()) {
    String oldName=rename.oldName;
    myMps2PsiMapper.clearFile(modelCopy,oldName);
  }
  for (  PsiJavaFile file : event.needReparse()) {
    if (!(file.isValid())) {
      String name=file.getName();
      for (      PsiFile f : file.getParent().getFiles()) {
        if (name.equals(f.getName()) && f instanceof PsiJavaFile) {
          file=(PsiJavaFile)f;
          break;
        }
      }
    }
    if (!(file.isValid())) {
      continue;
    }
    myMps2PsiMapper.clearFile(modelCopy,file.getName());
    SNode javaImports=getImports(file.getImportList());
    ASTConverter converter=new ASTConverter(myMps2PsiMapper);
    Set<SNodeId> roots=SetSequence.fromSet(new HashSet<SNodeId>());
    for (    PsiClass cls : file.getClasses()) {
      SNode node=converter.convertClass(cls);
      if (SNodeOperations.isInstanceOf(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101d9d3ca30L,"jetbrains.mps.baseLanguage.structure.Classifier")) && (javaImports != null)) {
        AttributeOperations.setAttribute(SNodeOperations.cast(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101d9d3ca30L,"jetbrains.mps.baseLanguage.structure.Classifier")),new IAttributeDescriptor.NodeAttribute(MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x53f7c33f069862f2L,"jetbrains.mps.baseLanguage.structure.JavaImports")),javaImports);
      }
      modelCopy.addRootNode(node);
      SetSequence.fromSet(roots).addElement(node.getNodeId());
    }
    if (SetSequence.fromSet(roots).isNotEmpty()) {
      MapSequence.fromMap(myRootsPerFile).put(file.getName(),roots);
    }
  }
  replace(new ModelLoadResult<SModel>(modelCopy,ModelLoadingState.FULLY_LOADED));
}
