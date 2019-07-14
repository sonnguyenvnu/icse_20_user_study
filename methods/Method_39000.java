/** 
 * Collects all filters.
 */
protected void collectActionFilters(){
  final Collection<? extends ActionFilter> filterValues=filtersManager.getAllFilters();
  filters=new ArrayList<>();
  filters.addAll(filterValues);
  filters.sort(Comparator.comparing(a -> a.getClass().getSimpleName()));
}
