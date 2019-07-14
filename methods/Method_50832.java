private void renderCategoryMeasurements(final TimedOperationCategory category,final Map<String,TimedResult> labeledMeasurements,final Writer writer) throws IOException {
  renderHeader(category.displayName(),writer);
  final TimedResult grandTotal=new TimedResult();
  final TreeSet<Map.Entry<String,TimedResult>> sortedKeySet=new TreeSet<>(new Comparator<Map.Entry<String,TimedResult>>(){
    @Override public int compare(    final Entry<String,TimedResult> o1,    final Entry<String,TimedResult> o2){
      return Long.compare(o1.getValue().selfTimeNanos.get(),o2.getValue().selfTimeNanos.get());
    }
  }
);
  sortedKeySet.addAll(labeledMeasurements.entrySet());
  for (  final Map.Entry<String,TimedResult> entry : sortedKeySet) {
    renderMeasurement(entry.getKey(),entry.getValue(),writer);
    grandTotal.mergeTimes(entry.getValue());
  }
  writer.write(PMD.EOL);
  renderMeasurement("Total " + category.displayName(),grandTotal,writer);
  writer.write(PMD.EOL);
}
