public void select(int selected){
  int index=selected + 1;
  stack.getItems().clear();
  local.getItems().clear();
  if (frames != null && index >= 0 && index < frames.size() - 1) {
    Frame<SourceValue> frame=frames.get(index);
    for (int s=0; s < frame.getStackSize(); s++) {
      SourceValue val;
      try {
        val=frame.getStack(s);
      }
 catch (      IndexOutOfBoundsException e) {
        val=new SourceValue(0);
      }
      stack.getItems().add(val);
    }
    for (int l=0; l < frame.getLocals(); l++) {
      SourceValue val;
      try {
        val=frame.getLocal(l);
      }
 catch (      IndexOutOfBoundsException e) {
        val=new SourceValue(0);
      }
      local.getItems().add(val);
    }
  }
  stack.refresh();
  local.refresh();
}
