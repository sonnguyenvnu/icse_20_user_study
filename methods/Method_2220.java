public void verifyCallerContext(@Nullable Object callerContext){
  if (mCallerContextVerifier != null) {
    mCallerContextVerifier.verifyCallerContext(callerContext);
  }
}
