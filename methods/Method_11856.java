private void arrayEquals(String message,Object expecteds,Object actuals,boolean outer) throws ArrayComparisonFailure {
  if (expecteds == actuals || Arrays.deepEquals(new Object[]{expecteds},new Object[]{actuals})) {
    return;
  }
  String header=message == null ? "" : message + ": ";
  String exceptionMessage=outer ? header : "";
  if (expecteds == null) {
    Assert.fail(exceptionMessage + "expected array was null");
  }
  if (actuals == null) {
    Assert.fail(exceptionMessage + "actual array was null");
  }
  int actualsLength=Array.getLength(actuals);
  int expectedsLength=Array.getLength(expecteds);
  if (actualsLength != expectedsLength) {
    header+="array lengths differed, expected.length=" + expectedsLength + " actual.length=" + actualsLength + "; ";
  }
  int prefixLength=Math.min(actualsLength,expectedsLength);
  for (int i=0; i < prefixLength; i++) {
    Object expected=Array.get(expecteds,i);
    Object actual=Array.get(actuals,i);
    if (isArray(expected) && isArray(actual)) {
      try {
        arrayEquals(message,expected,actual,false);
      }
 catch (      ArrayComparisonFailure e) {
        e.addDimension(i);
        throw e;
      }
catch (      AssertionError e) {
        throw new ArrayComparisonFailure(header,e,i);
      }
    }
 else {
      try {
        assertElementsEqual(expected,actual);
      }
 catch (      AssertionError e) {
        throw new ArrayComparisonFailure(header,e,i);
      }
    }
  }
  if (actualsLength != expectedsLength) {
    Object expected=getToStringableArrayElement(expecteds,expectedsLength,prefixLength);
    Object actual=getToStringableArrayElement(actuals,actualsLength,prefixLength);
    try {
      Assert.assertEquals(expected,actual);
    }
 catch (    AssertionError e) {
      throw new ArrayComparisonFailure(header,e,prefixLength);
    }
  }
}
