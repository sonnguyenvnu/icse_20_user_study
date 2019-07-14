private void newline(){
  if (indent == null) {
    return;
  }
  out.append("\n");
  for (int i=0; i < stack.size(); i++) {
    out.append(indent);
  }
}
