@Override protected MPSTreeNode rebuild(){
  setRootVisible(false);
  setGenerationMode(TraceSettings.isGenerationMode());
  if (TraceSettings.isTraceForSelectedNode() && mySelectedNode != null) {
    getSliceVars(myContextTracker.getOperation());
  }
  MPSTreeNode result=create(myContextTracker.getOperation(),true);
  if (result == null) {
    result=new TextTreeNode("Empty type system trace");
  }
  return result;
}
