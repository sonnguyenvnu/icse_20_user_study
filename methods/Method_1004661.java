@Override public String apply(Integer level){
  if (level < 0) {
    throw new IllegalArgumentException("Indent level must not be negative, got" + level);
  }
  StringBuilder indentBuilder=new StringBuilder();
  for (int i=0; i < level; i++) {
    indentBuilder.append(this.indent);
  }
  return indentBuilder.toString();
}
