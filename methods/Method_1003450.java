private void download(String target,String fileURL,String sha1Checksum){
  File targetFile=new File(target);
  if (targetFile.exists()) {
    return;
  }
  mkdirs(targetFile.getAbsoluteFile().getParentFile());
  ByteArrayOutputStream buff=new ByteArrayOutputStream();
  try {
    println("Downloading " + fileURL);
    URL url=new URL(fileURL);
    InputStream in=new BufferedInputStream(url.openStream());
    long last=System.nanoTime();
    int len=0;
    while (true) {
      long now=System.nanoTime();
      if (now > last + TimeUnit.SECONDS.toNanos(1)) {
        println("Downloaded " + len + " bytes");
        last=now;
      }
      int x=in.read();
      len++;
      if (x < 0) {
        break;
      }
      buff.write(x);
    }
    in.close();
  }
 catch (  IOException e) {
    throw new RuntimeException("Error downloading",e);
  }
  byte[] data=buff.toByteArray();
  String got=getSHA1(data);
  if (sha1Checksum == null) {
    println("SHA1 checksum: " + got);
  }
 else {
    if (!got.equals(sha1Checksum)) {
      throw new RuntimeException("SHA1 checksum mismatch; got: " + got);
    }
  }
  writeFile(targetFile,data);
}
