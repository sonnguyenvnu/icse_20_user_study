public static CountMatchingStrategy exactly(int expected){
  return new CountMatchingStrategy(CountMatchingStrategy.EQUAL_TO,expected);
}
