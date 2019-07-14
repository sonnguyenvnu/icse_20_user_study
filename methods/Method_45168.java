public static LogWriter fileLogWriter(final String filename,final Charset charset){
  return new FileLogWriter(filename,charset);
}
