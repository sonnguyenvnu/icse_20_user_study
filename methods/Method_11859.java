@Override protected void assertElementsEqual(Object expected,Object actual){
  if (expected instanceof Double) {
    Assert.assertEquals((Double)expected,(Double)actual,(Double)fDelta);
  }
 else {
    Assert.assertEquals((Float)expected,(Float)actual,(Float)fDelta);
  }
}
