private static Function<ParameterPair,Double> buildDistanceFunction(){
  return new Function<ParameterPair,Double>(){
    @Override public Double apply(    ParameterPair parameterPair){
      Parameter formal=parameterPair.formal();
      Parameter actual=parameterPair.actual();
      if (formal.isUnknownName() || actual.isUnknownName()) {
        return formal.index() == actual.index() ? 0.0 : 1.0;
      }
 else {
        return formal.name().equals(actual.name()) ? 0.0 : 1.0;
      }
    }
  }
;
}
