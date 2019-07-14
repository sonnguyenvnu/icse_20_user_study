public static CountMatchingStrategy moreThan(int expected){
  return new CountMatchingStrategy(CountMatchingStrategy.GREATER_THAN,expected);
}
