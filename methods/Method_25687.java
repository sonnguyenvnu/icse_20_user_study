/** 
 * This function looks explicitly for parameters named expected and actual. All other pairs with parameters other than these are given a distance of 0 if they are in their original position and Inf otherwise (i.e. they will not be considered for moving). For expected and actual, if the actual parameter name starts with expected or actual respectively then we consider it a perfect match otherwise we return a distance of 1.
 */
private static Function<ParameterPair,Double> buildDistanceFunction(){
  return new Function<ParameterPair,Double>(){
    @Override public Double apply(    ParameterPair parameterPair){
      Parameter formal=parameterPair.formal();
      Parameter actual=parameterPair.actual();
      String formalName=formal.name();
      String actualName=actual.name();
      if (formalName.equals("expected")) {
        if (actual.constant() || isEnumIdentifier(actual)) {
          return 0.0;
        }
        if (actualName.startsWith("expected")) {
          return 0.0;
        }
        return 1.0;
      }
      if (formalName.equals("actual")) {
        if (actual.constant() || isEnumIdentifier(actual)) {
          return 1.0;
        }
        if (actualName.startsWith("actual")) {
          return 0.0;
        }
        return 1.0;
      }
      return formal.index() == actual.index() ? 0.0 : Double.POSITIVE_INFINITY;
    }
  }
;
}
