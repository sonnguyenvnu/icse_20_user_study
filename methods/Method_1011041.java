public void execute(SNode node){
  assert ((SModule)PutReexportForExtendedClassifier_QuickFix.this.getField("moduleToImport")[0]) != null;
  boolean reexport=true;
  ((AbstractModule)PutReexportForExtendedClassifier_QuickFix.this.getField("module")[0]).addDependency(((SModule)PutReexportForExtendedClassifier_QuickFix.this.getField("moduleToImport")[0]).getModuleReference(),reexport);
}
