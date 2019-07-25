@Override public Collection<? extends IntentionExecutable> instances(SNode node,EditorContext editorContext){
  return myFactory.getTreeTransformers(node,editorContext.getSelectedNode(),editorContext).stream().map(TransformerBasedIntention::new).collect(Collectors.toList());
}
