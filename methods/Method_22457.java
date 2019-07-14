/** 
 * Adds the installed libraries to the listing of libraries, replacing any pre-existing libraries by the same name as one in the list.
 */
protected void updateInstalledList(List<Contribution> installed){
  for (  Contribution contribution : installed) {
    Contribution existingContribution=getContribution(contribution);
    if (existingContribution != null) {
      replaceContribution(existingContribution,contribution);
    }
 else {
      addContribution(contribution);
    }
  }
}
