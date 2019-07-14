private Description handleFileReader(NewClassTree tree,VisitorState state){
  Tree arg=getOnlyElement(tree.getArguments());
  Tree parent=state.getPath().getParentPath().getLeaf();
  Tree toReplace=BUFFERED_READER.matches(parent,state) ? parent : tree;
  Description.Builder description=buildDescription(tree);
  fileReaderFix(description,state,arg,toReplace);
  return description.build();
}
