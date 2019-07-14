/** 
 * Triggers a back press with an optional transition.
 */
private void goBack(){
  super.onBackPressed();
  final Pair<Integer,Integer> exitTransitions=exitTransition();
  if (exitTransitions != null) {
    overridePendingTransition(exitTransitions.first,exitTransitions.second);
  }
}
