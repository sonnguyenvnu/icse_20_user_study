static void register(final PositionCalculator calculator){
  POSITION_CALCULATORS.put(calculator.getType(),calculator);
}
