private void cell(final HTMLElement td,final ICoverageNode node) throws IOException {
  final int value=getValue(node.getCounter(entity));
  td.text(integerFormat.format(value));
}
