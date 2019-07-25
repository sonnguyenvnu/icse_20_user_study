@Override protected MPSTreeNode rebuild(){
  EditorMenuTraceInfo current=myRoot;
  return new EditorMenuTraceNode(current,new PathChildMenuTraceNodeInitializer(myPathFromRoot),myProject);
}
