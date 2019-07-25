private void error(String expected){
  throw new RuntimeException("Expected: " + expected + " got: " + xml.substring(pos,Math.min(pos + 1000,xml.length())));
}
