private void handleException(Throwable e) throws Throwable {
  if (isAnyExceptionExpected()) {
    assertThat(e,matcherBuilder.build());
  }
 else {
    throw e;
  }
}
