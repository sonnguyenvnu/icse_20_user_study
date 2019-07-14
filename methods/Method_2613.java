protected void walkToSave(ObjectOutput out) throws IOException {
  out.writeChar(c);
  out.writeInt(status.ordinal());
  if (status == Status.WORD_END_3 || status == Status.WORD_MIDDLE_2) {
    out.writeObject(value);
  }
  int childSize=0;
  if (child != null)   childSize=child.length;
  out.writeInt(childSize);
  if (child == null)   return;
  for (  BaseNode node : child) {
    node.walkToSave(out);
  }
}
