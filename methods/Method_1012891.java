private static void verify(String message,boolean condition){
  Assumption.assertTrue("Internal assertion failure : " + message,condition);
}
