private static File getTmpFile(){
  File tmpDir=FileUtils.getTempDirectory();
  String tmpFileName=(Math.random() * 10000 + "").replace(".","");
  return new File(tmpDir,tmpFileName);
}
