private ProcessRunner.ProcessResult writeException(final ByteArrayOutputStream baos,final Exception ex){
  try {
    baos.write(errPrefix.getBytes());
  }
 catch (  IOException ignore) {
  }
  ex.printStackTrace(new PrintStream(baos));
  return new ProcessRunner.ProcessResult(-1,baos.toString());
}
