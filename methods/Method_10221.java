private static void testMathExact(){
  System.out.println(Integer.MAX_VALUE);
  System.out.println(Integer.MAX_VALUE + 1);
  try {
    Math.addExact(Integer.MAX_VALUE,1);
  }
 catch (  ArithmeticException e) {
    System.err.println(e.getMessage());
  }
  try {
    Math.toIntExact(Long.MAX_VALUE);
  }
 catch (  ArithmeticException e) {
    System.err.println(e.getMessage());
  }
}
