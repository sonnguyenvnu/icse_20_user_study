@SuppressWarnings("PMD.CompareObjectsWithEquals") public List<Match> getMatches(){
  Collections.sort(matchList);
  return matchList;
}
