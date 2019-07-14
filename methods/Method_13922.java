private List<Object> parseForNextDataRow() throws IOException {
  List<Object> cells=getNextBatchOfDataValues(1);
  if (cells.size() == 0) {
    return null;
  }
  if (_dimensions.size() > 0) {
    for (int i=0; i < _dimensions.size(); i++) {
      Dimension d=_dimensions.get(i);
      if (d.next == d.values.size()) {
        d.next=0;
        if (i < _dimensions.size() - 1) {
          _dimensions.get(i + 1).next++;
        }
      }
      cells.add(0,d.values.get(d.next));
      if (i == 0) {
        d.next++;
      }
    }
  }
  return cells;
}
