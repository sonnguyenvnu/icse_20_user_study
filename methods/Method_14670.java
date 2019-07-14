public File getTempDir(){
  if (tempDir == null) {
    tempDir=(File)_config.getServletContext().getAttribute(JAVAX_SERVLET_CONTEXT_TEMPDIR);
    if (tempDir == null) {
      throw new RuntimeException("This app server doesn't support temp directories");
    }
  }
  return tempDir;
}
