private void write() throws IOException {
  String dir=outputDir + "/" + getDirName(className);
  mkdir(dir);
  String fileName=outputName();
  FileOutputStream fos=new FileOutputStream(fileName);
  fos.write(cv.toByteArray());
  fos.close();
  System.out.println("Wrote: " + fileName);
}
