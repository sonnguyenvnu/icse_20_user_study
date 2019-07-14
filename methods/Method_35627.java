public static CountMatchingStrategy moreThanOrExactly(int expected){
  return new CountMatchingStrategy(CountMatchingStrategy.GREATER_THAN_OR_EQUAL,expected);
}
