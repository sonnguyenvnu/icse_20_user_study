@Override public void apply(){
  if (myJavaModuleFacet.getModule() instanceof Solution) {
    SolutionDescriptor descriptor=(SolutionDescriptor)myJavaModuleFacet.getModule().getModuleDescriptor();
    assert descriptor != null;
    descriptor.setCompileInMPS(myCheckBox.isSelected());
    descriptor.setKind((SolutionKind)myComboBox.getSelectedItem());
    if (myExternalIdeaCompile != null) {
      descriptor.setNeedsExternalIdeaCompile(myExternalIdeaCompile.isSelected());
    }
  }
  final Collection<String> sourcePaths=myJavaModuleFacet.getModule().getModuleDescriptor().getSourcePaths();
  sourcePaths.clear();
  final Collection<String> sourcePathsTable=convertVirtualFile2StringPaths(mySourcePathsTableModel.getFiles());
  if (!sourcePathsTable.isEmpty()) {
    sourcePaths.addAll(sourcePathsTable);
  }
  mySourcePathsChanged=false;
  final Collection<String> libraryPaths=myJavaModuleFacet.getModule().getModuleDescriptor().getJavaLibs();
  libraryPaths.clear();
  final Collection<String> libraryPathsTable=convertVirtualFile2StringPaths(myLibrariesTableModel.getFiles());
  if (!libraryPathsTable.isEmpty()) {
    libraryPaths.addAll(libraryPathsTable);
  }
  myLibrariesChanged=false;
}
