@Override public void process(final StringBuilder out){
  separateByCommaOrSpace(out);
  out.append(ded.getTableNameForQuery());
  if (tableAlias != null) {
    out.append(' ').append(tableAlias);
  }
}
