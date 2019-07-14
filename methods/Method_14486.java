@Override public void save(Writer writer,Properties options) throws IOException {
  writer.write("row=");
  writer.write(Integer.toString(rowIndex));
  writer.write('\n');
  writer.write("newStarred=");
  writer.write(Boolean.toString(newStarred));
  writer.write('\n');
  writer.write("oldStarred=");
  writer.write(Boolean.toString(oldStarred));
  writer.write('\n');
  writer.write("/ec/\n");
}
