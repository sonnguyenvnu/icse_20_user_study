private void fetch(final int start){
  lastFetch=start;
  requestFactory.schoolCalendarRequest().getPeople(start,numRows,filter).fire(new Receiver<List<PersonProxy>>(){
    @Override public void onSuccess(    List<PersonProxy> response){
      if (lastFetch != start) {
        return;
      }
      int responses=response.size();
      table.setRowData(start,response);
      pager.setPageStart(start);
      if (start == 0 || !table.isRowCountExact()) {
        table.setRowCount(start + responses,responses < numRows);
      }
    }
  }
);
}
