/** 
 * Collects all action results.
 */
protected void collectActionResults(){
  final Collection<ActionResult> resultsValues=resultsManager.getAllActionResults();
  results=new ArrayList<>();
  results.addAll(resultsValues);
  results.sort(Comparator.comparing(a -> a.getClass().getSimpleName()));
}
