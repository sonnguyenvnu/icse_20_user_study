@Override public void save(Writer writer,Properties options) throws IOException {
  writer.write("row=");
  writer.write(Integer.toString(rowIndex));
  writer.write('\n');
  writer.write("newFlagged=");
  writer.write(Boolean.toString(newFlagged));
  writer.write('\n');
  writer.write("oldFlagged=");
  writer.write(Boolean.toString(oldFlagged));
  writer.write('\n');
  writer.write("/ec/\n");
}
