@Override public void save(Writer writer,Properties options) throws IOException {
  writer.write("oldColumnName=");
  writer.write(_oldColumnName);
  writer.write('\n');
  writer.write("newColumnName=");
  writer.write(_newColumnName);
  writer.write('\n');
  writer.write("/ec/\n");
}
