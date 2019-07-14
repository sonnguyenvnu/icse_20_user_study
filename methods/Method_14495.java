static public ColumnGroup load(String s) throws IOException {
  return ParsingUtilities.mapper.readValue(s,ColumnGroup.class);
}
