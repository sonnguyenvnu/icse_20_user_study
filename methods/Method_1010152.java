@Override public void trace(@Nullable SNodeId input,@NotNull List<SNodeId> output,@NotNull SNodeReference templateNode){
  myCurrent.add(input,output,templateNode);
}
