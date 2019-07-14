public void computeFacets(){
  if (_config.getMode().equals(Mode.RowBased)) {
    for (    Facet facet : _facets) {
      FilteredRows filteredRows=getFilteredRows(facet);
      facet.computeChoices(_project,filteredRows);
    }
  }
 else   if (_config.getMode().equals(Mode.RecordBased)) {
    for (    Facet facet : _facets) {
      FilteredRecords filteredRecords=getFilteredRecords(facet);
      facet.computeChoices(_project,filteredRecords);
    }
  }
 else {
    throw new InternalError("Unknown mode.");
  }
}
