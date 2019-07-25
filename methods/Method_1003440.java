private static void check(String a,String b){
  if (!a.equals(b)) {
    throw new RuntimeException("mismatch: " + a + " <> " + b);
  }
}
