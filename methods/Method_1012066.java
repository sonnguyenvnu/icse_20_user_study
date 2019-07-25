private void populate(){
  List<SModel> regularModels=new ArrayList<>();
  List<SModel> tests=new ArrayList<>();
  List<SModel> stubs=new ArrayList<>();
  for (  SModel modelDescriptor : getModule().getModels()) {
    if (TemporaryModels.isTemporary(modelDescriptor)) {
      continue;
    }
    if (SModelStereotype.isDescriptorModel(modelDescriptor)) {
      continue;
    }
    if (SModelStereotype.isStubModel(modelDescriptor)) {
      stubs.add(modelDescriptor);
    }
 else     if (SModelStereotype.isTestModel(modelDescriptor)) {
      tests.add(modelDescriptor);
    }
 else {
      regularModels.add(modelDescriptor);
    }
  }
  if (!regularModels.isEmpty()) {
    new SModelsSubtree(this).create(regularModels);
  }
  if (!tests.isEmpty()) {
    TestsTreeNode testsNode=new TestsTreeNode();
    new SModelsSubtree(testsNode).create(tests);
    add(testsNode);
  }
  if (!stubs.isEmpty()) {
    StubsTreeNode stubsNode=new StubsTreeNode();
    new SModelsSubtree(stubsNode).create(stubs);
    add(stubsNode);
  }
}
