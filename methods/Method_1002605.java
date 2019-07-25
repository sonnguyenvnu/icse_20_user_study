static Appendable indent(final Appendable appendable,final int level) throws IOException {
  Appendable out=appendable;
  for (int i=0; i < level; i++) {
    out=out.append(INDENT);
  }
  return out;
}
