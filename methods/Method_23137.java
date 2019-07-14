private void writeAudioOnly(File movieFile,File audioFile,String streaming) throws IOException {
  File tmpFile=streaming.equals("none") ? movieFile : new File(movieFile.getPath() + ".tmp");
  int length=(int)Math.min(Integer.MAX_VALUE,audioFile.length());
  ProgressMonitor p=new ProgressMonitor(MovieMaker.this,Language.interpolate("movie_maker.progress.creating_file_name",movieFile.getName()),Language.text("movie_maker.progress.initializing"),0,length);
  AudioInputStream audioIn=null;
  QuickTimeWriter qtOut=null;
  try {
    qtOut=new QuickTimeWriter(tmpFile);
    if (audioFile.getName().toLowerCase().endsWith(".mp3")) {
      audioIn=new MP3AudioInputStream(audioFile);
    }
 else {
      audioIn=AudioSystem.getAudioInputStream(audioFile);
    }
    AudioFormat audioFormat=audioIn.getFormat();
    qtOut.addAudioTrack(audioFormat);
    boolean isVBR=audioFormat.getProperty("vbr") != null && ((Boolean)audioFormat.getProperty("vbr")).booleanValue();
    int asSize=audioFormat.getFrameSize();
    int nbOfFramesInBuffer=isVBR ? 1 : Math.max(1,1024 / asSize);
    int asDuration=(int)(audioFormat.getSampleRate() / audioFormat.getFrameRate());
    long count=0;
    byte[] audioBuffer=new byte[asSize * nbOfFramesInBuffer];
    for (int bytesRead=audioIn.read(audioBuffer); bytesRead != -1; bytesRead=audioIn.read(audioBuffer)) {
      if (bytesRead != 0) {
        int framesRead=bytesRead / asSize;
        qtOut.writeSamples(0,framesRead,audioBuffer,0,bytesRead,asDuration);
        count+=bytesRead;
        p.setProgress((int)count);
      }
      if (isVBR) {
        audioFormat=audioIn.getFormat();
        if (audioFormat == null) {
          break;
        }
        asSize=audioFormat.getFrameSize();
        asDuration=(int)(audioFormat.getSampleRate() / audioFormat.getFrameRate());
        if (audioBuffer.length < asSize) {
          audioBuffer=new byte[asSize];
        }
      }
    }
    audioIn.close();
    audioIn=null;
    if (streaming.equals("fastStart")) {
      qtOut.toWebOptimizedMovie(movieFile,false);
      tmpFile.delete();
    }
 else     if (streaming.equals("fastStartCompressed")) {
      qtOut.toWebOptimizedMovie(movieFile,true);
      tmpFile.delete();
    }
    qtOut.close();
    qtOut=null;
  }
 catch (  UnsupportedAudioFileException e) {
    IOException ioe=new IOException(e.getMessage());
    ioe.initCause(e);
    throw ioe;
  }
 finally {
    p.close();
    if (audioIn != null) {
      audioIn.close();
    }
    if (qtOut != null) {
      qtOut.close();
    }
  }
}
