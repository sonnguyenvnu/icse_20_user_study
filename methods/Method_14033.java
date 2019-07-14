public FilteredRows getFilteredRows(Facet except){
  if (_config.getMode().equals(Mode.RecordBased)) {
    return new FilteredRecordsAsFilteredRows(getFilteredRecords(except));
  }
 else   if (_config.getMode().equals(Mode.RowBased)) {
    ConjunctiveFilteredRows cfr=new ConjunctiveFilteredRows();
    for (    Facet facet : _facets) {
      if (facet != except) {
        RowFilter rowFilter=facet.getRowFilter(_project);
        if (rowFilter != null) {
          cfr.add(rowFilter);
        }
      }
    }
    return cfr;
  }
  throw new InternalError("Unknown mode.");
}
