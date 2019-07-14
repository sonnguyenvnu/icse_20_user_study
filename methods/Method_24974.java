Container createScrollPane(){
  JScrollPane scrollPane=new JScrollPane();
  tree=new Outline();
  scrollPane.setViewportView(tree);
  rootNode=new DefaultMutableTreeNode("root");
  builtins=new DefaultMutableTreeNode("Processing");
  treeModel=new DefaultTreeModel(rootNode);
  model=DefaultOutlineModel.createOutlineModel(treeModel,new VariableRowModel(),true,Language.text("debugger.name"));
  ExpansionHandler expansionHandler=new ExpansionHandler();
  model.getTreePathSupport().addTreeWillExpandListener(expansionHandler);
  model.getTreePathSupport().addTreeExpansionListener(expansionHandler);
  tree.setModel(model);
  tree.setRootVisible(false);
  tree.setRenderDataProvider(new OutlineRenderer());
  tree.setColumnHidingAllowed(false);
  tree.setAutoscrolls(false);
  TableColumn valueColumn=tree.getColumnModel().getColumn(1);
  valueColumn.setCellRenderer(new ValueCellRenderer());
  valueColumn.setCellEditor(new ValueCellEditor());
  callStack=new ArrayList<>();
  locals=new ArrayList<>();
  thisFields=new ArrayList<>();
  declaredThisFields=new ArrayList<>();
  scrollPane.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
  scrollPane.setPreferredSize(new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT));
  return scrollPane;
}
