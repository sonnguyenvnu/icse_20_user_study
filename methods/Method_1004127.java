public void item(final HTMLElement td,final ITableItem item,final Resources resources,final ReportOutputFolder base) throws IOException {
  if (max > 0) {
    final ICounter counter=item.getNode().getCounter(entity);
    final int missed=counter.getMissedCount();
    bar(td,missed,Resources.REDBAR,resources,base);
    final int covered=counter.getCoveredCount();
    bar(td,covered,Resources.GREENBAR,resources,base);
  }
}
