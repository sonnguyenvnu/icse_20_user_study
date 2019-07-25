static SNode resolve(@NotNull String refText,@NotNull SNode contextClassifier,boolean includeAncestors){
  SModel contextNodeModel=SNodeOperations.getModel(contextClassifier);
  assert contextNodeModel != null;
  Iterable<SNode> pathToRoot=SNodeOperations.getNodeAncestors(contextClassifier,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101d9d3ca30L,"jetbrains.mps.baseLanguage.structure.Classifier"),true);
  assert Sequence.fromIterable(pathToRoot).isNotEmpty() : "Shall contain at least contextClassifier";
  StringTokenizer tokenizer=new StringTokenizer(refText,".");
  if (!(tokenizer.hasMoreTokens())) {
    return null;
  }
  String token=tokenizer.nextToken();
  assert token != null;
  for (  SNode pathElement : Sequence.fromIterable(pathToRoot)) {
    if (SNodeOperations.isInstanceOf(pathElement,SNodeOperations.asSConcept(anonymousClassConcept))) {
      continue;
    }
    if (token.equals(SPropertyOperations.getString(pathElement,MetaAdapterFactory.getProperty(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x110396eaaa4L,0x110396ec041L,"name")))) {
      return construct(pathElement,tokenizer);
    }
    for (    SNode nested : Sequence.fromIterable(getImmediateNestedClassifiers(pathElement))) {
      if (token.equals(SPropertyOperations.getString(nested,MetaAdapterFactory.getProperty(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x110396eaaa4L,0x110396ec041L,"name")))) {
        return construct(nested,tokenizer);
      }
    }
  }
  Iterable<SNode> classesWhoseSuperIsInteresting=(includeAncestors ? pathToRoot : Sequence.fromIterable(pathToRoot).skip(1));
  for (  SNode enclosing : Sequence.fromIterable(classesWhoseSuperIsInteresting)) {
    for (    SNode ancestor : Sequence.fromIterable(getAncestors(enclosing))) {
      for (      SNode nested : Sequence.fromIterable(getImmediateNestedClassifiers(ancestor))) {
        if (token.equals(SPropertyOperations.getString(nested,MetaAdapterFactory.getProperty(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x110396eaaa4L,0x110396ec041L,"name")))) {
          return construct(nested,tokenizer);
        }
      }
    }
  }
  SNode root=Sequence.fromIterable(pathToRoot).last();
  SNode javaImports=AttributeOperations.getAttribute(root,new IAttributeDescriptor.NodeAttribute(MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x53f7c33f069862f2L,"jetbrains.mps.baseLanguage.structure.JavaImports")));
  if (javaImports == null) {
    return null;
  }
  AbstractModule module=(AbstractModule)contextNodeModel.getModule();
  SearchScope moduleScope=(module == null ? GlobalScope.getInstance() : module.getScope());
  final List<SModel> moduleScopeModels=ListSequence.fromListWithValues(new ArrayList<SModel>(),moduleScope.getModels());
  ClassifierResolveUtils.ModelsByName modelsByName=new ClassifierResolveUtils.ModelsByName(moduleScopeModels);
  final String javaStubStereotype=SModelStereotype.getStubStereotypeForId(LanguageID.JAVA);
  for (  SNode imp : ListSequence.fromList(SLinkOperations.getChildren(javaImports,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x53f7c33f069862f2L,0x64c0181e6020a7L,"entries"))).where(new IWhereFilter<SNode>(){
    public boolean accept(    SNode it){
      return !(SPropertyOperations.getBoolean(it,MetaAdapterFactory.getProperty(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x64c0181e603bcfL,0x64c0181e603bd0L,"onDemand")));
    }
  }
)) {
    if (!(token.equals(Tokens__BehaviorDescriptor.lastToken_id17WpDCYLyrY.invoke(imp)))) {
      continue;
    }
    String fqName=SPropertyOperations.getString(imp,MetaAdapterFactory.getProperty(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x5a98df4004080866L,0x1996ec29712bdd92L,"tokens"));
    Iterable<SNode> matches=modelsByName.resolveClassifierByFqNameWithNonStubPriority(fqName,javaStubStereotype);
    return (Sequence.fromIterable(matches).count() == 1 ? construct(Sequence.fromIterable(matches).first(),tokenizer) : null);
  }
  List<Object> javaImportedThings=ListSequence.fromList(new ArrayList<Object>());
  ListSequence.fromList(javaImportedThings).addElement(contextNodeModel);
  String ourPkgName=contextNodeModel.getName().getLongName();
  List<SModel> samePackageModels=modelsByName.getByName(ourPkgName);
  samePackageModels.remove(contextNodeModel);
  ListSequence.fromList(javaImportedThings).addSequence(ListSequence.fromList(samePackageModels));
  SModule jdkModule=moduleScope.resolve(PersistenceFacade.getInstance().createModuleReference("6354ebe7-c22a-4a0f-ac54-50b52ab9b065(JDK)"));
  SModel javaLangModel=(jdkModule == null ? null : jdkModule.getModel(new JavaPackageNameStub("java.lang").asModelId()));
  if (javaLangModel != null) {
    ListSequence.fromList(javaImportedThings).addElement(javaLangModel);
  }
  for (  SNode imp : ListSequence.fromList(SLinkOperations.getChildren(javaImports,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x53f7c33f069862f2L,0x64c0181e6020a7L,"entries"))).where(new IWhereFilter<SNode>(){
    public boolean accept(    SNode it){
      return SPropertyOperations.getBoolean(it,MetaAdapterFactory.getProperty(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x64c0181e603bcfL,0x64c0181e603bd0L,"onDemand"));
    }
  }
)) {
    String fqName=SPropertyOperations.getString(imp,MetaAdapterFactory.getProperty(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x5a98df4004080866L,0x1996ec29712bdd92L,"tokens"));
    if (SPropertyOperations.getBoolean(imp,MetaAdapterFactory.getProperty(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x64c0181e603bcfL,0x4d5c30eb30af1572L,"static"))) {
      ListSequence.fromList(javaImportedThings).addSequence(ListSequence.fromList(modelsByName.resolveClassifierByFqNameWithNonStubPriority(fqName,javaStubStereotype)));
    }
 else {
      List<SModel> models=modelsByName.getByName(fqName);
      if (!(models.isEmpty())) {
        ListSequence.fromList(javaImportedThings).addSequence(ListSequence.fromList(models));
      }
 else {
        ListSequence.fromList(javaImportedThings).addSequence(ListSequence.fromList(modelsByName.resolveClassifierByFqNameWithNonStubPriority(fqName,javaStubStereotype)));
      }
    }
  }
  for (  Object thing : javaImportedThings) {
    if (!(thing.equals(contextNodeModel)) && thing instanceof SModel && (((SModel)thing).getModule() instanceof TransientSModule)) {
      continue;
    }
    SNode theResult=null;
    boolean wasResult=false;
    Iterable<SNode> roots=(thing instanceof SModel ? SModelOperations.roots(((SModel)thing),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101d9d3ca30L,"jetbrains.mps.baseLanguage.structure.Classifier")) : SNodeOperations.ofConcept(SLinkOperations.getChildren(SNodeOperations.cast(((SNode)thing),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101d9d3ca30L,"jetbrains.mps.baseLanguage.structure.Classifier")),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101d9d3ca30L,0x4a9a46de59132803L,"member")),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101d9d3ca30L,"jetbrains.mps.baseLanguage.structure.Classifier")));
    for (    SNode r : roots) {
      if (token.equals(SPropertyOperations.getString(r,MetaAdapterFactory.getProperty(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x110396eaaa4L,0x110396ec041L,"name")))) {
        if (theResult != null) {
          return null;
        }
        theResult=construct(r,tokenizer);
        wasResult=true;
      }
    }
    if (wasResult) {
      return theResult;
    }
  }
  return null;
}
