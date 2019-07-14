private void writePkgInfo(File file) throws IOException {
  Writer out=new BufferedWriter(new FileWriter(file));
  try {
    out.write(OS_TYPE_CODE + signature);
    out.flush();
  }
  finally {
    out.close();
  }
}
