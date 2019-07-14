public void filterLibraries(String category,List<String> filters){
  filter.setCategoryFilter(category);
  filter.setStringFilters(filters);
  model.fireTableDataChanged();
}
