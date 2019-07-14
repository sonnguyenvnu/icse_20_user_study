private void writeVideoAndAudio(File movieFile,File[] imgFiles,File audioFile,int width,int height,double fps,QuickTimeWriter.VideoFormat videoFormat,String streaming) throws IOException {
  File tmpFile=streaming.equals("none") ? movieFile : new File(movieFile.getPath() + ".tmp");
  ProgressMonitor p=new ProgressMonitor(MovieMaker.this,Language.interpolate("movie_maker.progress.creating_file_name",movieFile.getName()),Language.text("movie_maker.progress.creating_output_file"),0,imgFiles.length);
  AudioInputStream audioIn=null;
  QuickTimeWriter qtOut=null;
  BufferedImage imgBuffer=null;
  Graphics2D g=null;
  try {
    if (audioFile.getName().toLowerCase().endsWith(".mp3")) {
      audioIn=new MP3AudioInputStream(audioFile);
    }
 else {
      audioIn=AudioSystem.getAudioInputStream(audioFile);
    }
    AudioFormat audioFormat=audioIn.getFormat();
    boolean isVBR=audioFormat.getProperty("vbr") != null && ((Boolean)audioFormat.getProperty("vbr")).booleanValue();
    int asDuration=(int)(audioFormat.getSampleRate() / audioFormat.getFrameRate());
    int vsDuration=100;
    qtOut=new QuickTimeWriter(videoFormat == QuickTimeWriter.VideoFormat.RAW ? movieFile : tmpFile);
    qtOut.addAudioTrack(audioFormat);
    qtOut.addVideoTrack(videoFormat,(int)(fps * vsDuration),width,height);
    int asSize;
    byte[] audioBuffer;
    if (isVBR) {
      asSize=audioFormat.getFrameSize();
      audioBuffer=new byte[asSize];
    }
 else {
      asSize=audioFormat.getChannels() * audioFormat.getSampleSizeInBits() / 8;
      audioBuffer=new byte[(int)(qtOut.getMediaTimeScale(0) / 2 * asSize)];
    }
    if (true) {
      imgBuffer=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
      g=imgBuffer.createGraphics();
      g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
    }
    int movieTime=0;
    int imgIndex=0;
    boolean isAudioDone=false;
    while ((imgIndex < imgFiles.length || !isAudioDone) && !p.isCanceled()) {
      movieTime+=qtOut.getMovieTimeScale() / 2;
      while (!isAudioDone && qtOut.getTrackDuration(0) < movieTime + qtOut.getMovieTimeScale()) {
        int len=audioIn.read(audioBuffer);
        if (len == -1) {
          isAudioDone=true;
        }
 else {
          qtOut.writeSamples(0,len / asSize,audioBuffer,0,len,asDuration);
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
      for (; imgIndex < imgFiles.length && qtOut.getTrackDuration(1) < movieTime; ++imgIndex) {
        p.setProgress(imgIndex);
        File f=imgFiles[imgIndex];
        if (f == null)         continue;
        p.setNote(Language.interpolate("movie_maker.progress.processing",f.getName()));
        if (false) {
          qtOut.writeSample(1,f,vsDuration);
        }
 else {
          BufferedImage fImg=readImage(f);
          if (fImg == null)           continue;
          g.drawImage(fImg,0,0,width,height,null);
          fImg.flush();
          qtOut.writeFrame(1,imgBuffer,vsDuration);
        }
      }
    }
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
    if (qtOut != null) {
      qtOut.close();
    }
    if (audioIn != null) {
      audioIn.close();
    }
    if (g != null) {
      g.dispose();
    }
    if (imgBuffer != null) {
      imgBuffer.flush();
    }
  }
}
