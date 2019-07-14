private void findCommonSuffix(){
  int expectedSuffix=fExpected.length() - 1;
  int actualSuffix=fActual.length() - 1;
  for (; actualSuffix >= fPrefix && expectedSuffix >= fPrefix; actualSuffix--, expectedSuffix--) {
    if (fExpected.charAt(expectedSuffix) != fActual.charAt(actualSuffix)) {
      break;
    }
  }
  fSuffix=fExpected.length() - expectedSuffix;
}
