@Override public List<Object> getNextRowOfCells() throws IOException {
  if (_line == null) {
    _line=_lnReader.readLine();
  }
  if (_line == null) {
    return null;
  }
  if (_dimensions == null) {
    return parseMetadataPrologueForColumnNames();
  }
 else {
    return parseForNextDataRow();
  }
}
