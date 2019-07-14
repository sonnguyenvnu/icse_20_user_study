private double normalisedLevenshteinDistance(String one,String two){
  if (one == null || two == null) {
    return 1.0;
  }
  double maxDistance=Math.max(one.length(),two.length());
  double actualDistance=getLevenshteinDistance(one,two);
  return (actualDistance / maxDistance);
}
