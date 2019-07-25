public static Optional<Double> divide(List<Object> argList){
  if (argList == null || argList.size() != 2) {
    return Optional.empty();
  }
  Optional<? extends Number> numerator=Objects.toNumber(argList.get(0));
  Optional<? extends Number> denominator=Objects.toNumber(argList.get(1));
  if (numerator.isPresent() && denominator.isPresent()) {
    Double drDoubleValue=denominator.get().doubleValue();
    if (drDoubleValue == 0) {
      return Optional.empty();
    }
    Double nrDoubleValue=numerator.get().doubleValue();
    Double result=nrDoubleValue / drDoubleValue;
    return Optional.of(result);
  }
  return Optional.empty();
}
