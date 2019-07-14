private static void assertGreaterOrEqual(int value1,int value2,String name1,String name2){
  Assertions.checkArgument(value1 >= value2,name1 + " cannot be less than " + name2);
}
