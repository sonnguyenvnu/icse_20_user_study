protected DefaultMutableTreeNode buildTree(){
  DefaultMutableTreeNode root=new DefaultMutableTreeNode();
  try {
    File[] examples=mode.getExampleCategoryFolders();
    for (    File subFolder : examples) {
      DefaultMutableTreeNode subNode=new DefaultMutableTreeNode(subFolder.getName());
      if (base.addSketches(subNode,subFolder,true)) {
        root.add(subNode);
      }
    }
    DefaultMutableTreeNode foundationLibraries=new DefaultMutableTreeNode(Language.text("examples.core_libraries"));
    for (    Library lib : mode.coreLibraries) {
      if (lib.hasExamples()) {
        DefaultMutableTreeNode libNode=new DefaultMutableTreeNode(lib.getName());
        if (base.addSketches(libNode,lib.getExamplesFolder(),true)) {
          foundationLibraries.add(libNode);
        }
      }
    }
    if (foundationLibraries.getChildCount() > 0) {
      root.add(foundationLibraries);
    }
    DefaultMutableTreeNode contributedLibExamples=new DefaultMutableTreeNode(Language.text("examples.libraries"));
    for (    Library lib : mode.contribLibraries) {
      if (lib.hasExamples()) {
        DefaultMutableTreeNode libNode=new DefaultMutableTreeNode(lib.getName());
        base.addSketches(libNode,lib.getExamplesFolder(),true);
        contributedLibExamples.add(libNode);
      }
    }
    if (contributedLibExamples.getChildCount() > 0) {
      root.add(contributedLibExamples);
    }
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
  DefaultMutableTreeNode contributedExamplesNode=buildContribTree();
  if (contributedExamplesNode.getChildCount() > 0) {
    root.add(contributedExamplesNode);
  }
  return root;
}
