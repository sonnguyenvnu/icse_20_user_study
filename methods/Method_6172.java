public static AudioInfo getAudioInfo(File file){
  try {
    byte header[]=new byte[12];
    RandomAccessFile randomAccessFile=new RandomAccessFile(file,"r");
    randomAccessFile.readFully(header,0,8);
    randomAccessFile.close();
    InputStream input=new BufferedInputStream(new FileInputStream(file));
    if (header[4] == 'f' && header[5] == 't' && header[6] == 'y' && header[7] == 'p') {
      return new M4AInfo(input);
    }
 else     if (file.getAbsolutePath().endsWith("mp3")) {
      return new MP3Info(input,file.length());
    }
 else {
      return null;
    }
  }
 catch (  Exception e) {
    return null;
  }
}
