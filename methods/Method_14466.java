static public void writeOldColumnGroups(Writer writer,Properties options,List<ColumnGroup> oldColumnGroups) throws IOException {
  writer.write("oldColumnGroupCount=");
  writer.write(Integer.toString(oldColumnGroups.size()));
  writer.write('\n');
  for (  ColumnGroup cg : oldColumnGroups) {
    ParsingUtilities.saveWriter.writeValue(writer,cg);
    writer.write('\n');
  }
}
