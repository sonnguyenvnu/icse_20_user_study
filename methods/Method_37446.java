@Override public void printStackTrace(final PrintWriter pw){
synchronized (pw) {
    super.printStackTrace(pw);
    if ((cause != null) && showCauseDetails) {
      Throwable rootCause=ExceptionUtil.getRootCause(cause);
      pw.println(CAUSE_DIV);
      rootCause.printStackTrace(pw);
    }
  }
}
