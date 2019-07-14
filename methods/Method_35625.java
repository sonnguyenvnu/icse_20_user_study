public static CountMatchingStrategy lessThanOrExactly(int expected){
  return new CountMatchingStrategy(CountMatchingStrategy.LESS_THAN_OR_EQUAL,expected);
}
