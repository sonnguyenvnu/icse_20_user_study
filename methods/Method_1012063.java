private void populate(){
  TextTreeNode extendedDevkits=new TextTreeNode("extended devkits");
  for (  DevKit d : getModule().getExtendedDevKits()) {
    extendedDevkits.add(ProjectModuleTreeNode.createFor(myProject,d));
  }
  add(extendedDevkits);
  TextTreeNode exportedLangs=new TextTreeNode("exported languages");
  for (  Language l : getModule().getExportedLanguages()) {
    exportedLangs.add(ProjectModuleTreeNode.createFor(myProject,l));
  }
  add(exportedLangs);
  TextTreeNode exportedSolutions=new TextTreeNode("exported solutions");
  for (  Solution s : getModule().getExportedSolutions()) {
    exportedSolutions.add(ProjectModuleTreeNode.createFor(myProject,s));
  }
  add(exportedSolutions);
}
