public static MatchResult aggregateWeighted(final List<WeightedMatchResult> matchResults){
  return new MatchResult(){
    @Override public boolean isExactMatch(){
      return all(matchResults,ARE_EXACT_MATCH);
    }
    @Override public double getDistance(){
      double totalDistance=0;
      double sizeWithWeighting=0;
      for (      WeightedMatchResult matchResult : matchResults) {
        totalDistance+=matchResult.getDistance();
        sizeWithWeighting+=matchResult.getWeighting();
      }
      return (totalDistance / sizeWithWeighting);
    }
  }
;
}
