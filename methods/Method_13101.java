public void show(Collection<Future<Indexes>> collectionOfFutureIndexes,Container.Entry entry,String typeName){
  this.collectionOfFutureIndexes=collectionOfFutureIndexes;
  SwingUtil.invokeLater(() -> {
    updateTree(entry,typeName);
    openTypeHierarchyDialog.setVisible(true);
    openTypeHierarchyTree.requestFocus();
  }
);
}
