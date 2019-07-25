public void extract(String imagePath,byte[] imageData) throws IOException {
  File imageFile=new File(baseDir,imagePath);
  imageFile.getParentFile().mkdirs();
  InputStream in=null;
  OutputStream out=null;
  try {
    in=new ByteArrayInputStream(imageData);
    out=new FileOutputStream(imageFile);
    IOUtils.copy(in,out);
  }
  finally {
    if (in != null) {
      IOUtils.closeQuietly(in);
    }
    if (out != null) {
      IOUtils.closeQuietly(out);
    }
  }
}
