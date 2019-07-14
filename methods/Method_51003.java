private void tryToInsertIntoFile(int beginLine,int beginColumn,final String textToInsert) throws IOException {
  final int offset=mapToOffset(beginLine,beginColumn);
  writeUntilOffsetReached(offset);
  writer.write(textToInsert);
}
