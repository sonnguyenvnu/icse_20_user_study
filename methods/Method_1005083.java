protected void _next(final List<Cell> input,final List<Cell> output) throws IOException {
  List<LazyElementCell> elementCells=new ArrayList<>(input.size());
  for (  final Cell cell : input) {
    elementCells.add(new LazyElementCell(cell,serialisation,includeMatchedVertex));
  }
  for (  final GafferScannerProcessor processor : processors) {
    elementCells=processor.process(elementCells);
  }
  for (  final LazyElementCell elementCell : elementCells) {
    output.add(elementCell.getCell());
  }
}
