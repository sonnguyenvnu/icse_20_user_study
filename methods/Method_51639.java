private JComponent createASTPanel(){
  astTreeWidget.setCellRenderer(createNoImageTreeCellRenderer());
  TreeSelectionModel model=astTreeWidget.getSelectionModel();
  model.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
  model.addTreeSelectionListener(new SymbolTableListener());
  model.addTreeSelectionListener(new CodeHighlightListener());
  return new JScrollPane(astTreeWidget);
}
