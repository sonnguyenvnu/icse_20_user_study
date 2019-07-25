private int instrument(final Instrumenter instrumenter,final Resource resource){
  final File file=new File(destdir,resource.getName());
  file.getParentFile().mkdirs();
  try {
    InputStream input=null;
    OutputStream output=null;
    try {
      input=resource.getInputStream();
      output=new FileOutputStream(file);
      return instrumenter.instrumentAll(input,output,resource.getName());
    }
  finally {
      FileUtils.close(input);
      FileUtils.close(output);
    }
  }
 catch (  final Exception e) {
    file.delete();
    throw new BuildException(format("Error while instrumenting %s",resource),e,getLocation());
  }
}
