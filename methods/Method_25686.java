/** 
 * Computes the distance between a formal and actual parameter. If either is a null literal then the distance is zero (null matches everything). If both have a name then we compute the normalised NeedlemanWunschEditDistance. Otherwise, one of the names is unknown and so we return 0 distance between it and its original parameter and infinite distance between all others.
 */
private static final Function<ParameterPair,Double> buildDefaultDistanceFunction(){
  return new Function<ParameterPair,Double>(){
    @Override public Double apply(    ParameterPair pair){
      if (pair.formal().isNullLiteral() || pair.actual().isNullLiteral()) {
        return 0.0;
      }
      if (!pair.formal().isUnknownName() && !pair.actual().isUnknownName()) {
        String normalizedSource=NamingConventions.convertToLowerUnderscore(pair.formal().name());
        String normalizedTarget=NamingConventions.convertToLowerUnderscore(pair.actual().name());
        return NeedlemanWunschEditDistance.getNormalizedEditDistance(normalizedSource,normalizedTarget,false,8,8,1);
      }
      return pair.formal().index() == pair.actual().index() ? 0.0 : Double.POSITIVE_INFINITY;
    }
  }
;
}
