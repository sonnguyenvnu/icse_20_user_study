@Override public void writeExternal(ObjectOutput out) throws IOException {
  out.writeInt(size);
  for (  BaseNode node : child) {
    if (node == null) {
      out.writeInt(0);
    }
 else {
      out.writeInt(1);
      node.walkToSave(out);
    }
  }
}
