private void cell(final HTMLElement td,final ICoverageNode node) throws IOException {
  final ICounter counter=node.getCounter(entity);
  final int total=counter.getTotalCount();
  if (total == 0) {
    td.text("n/a");
  }
 else {
    td.text(format(counter.getCoveredRatio()));
  }
}
