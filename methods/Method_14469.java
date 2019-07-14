@Override public void save(Writer writer,Properties options) throws IOException {
  writer.write("columnName=");
  writer.write(_columnName);
  writer.write('\n');
  writer.write("oldColumnIndex=");
  writer.write(Integer.toString(_oldColumnIndex));
  writer.write('\n');
  writer.write("newColumnIndex=");
  writer.write(Integer.toString(_newColumnIndex));
  writer.write('\n');
  writeOldColumnGroups(writer,options,_oldColumnGroups);
  writer.write("/ec/\n");
}
