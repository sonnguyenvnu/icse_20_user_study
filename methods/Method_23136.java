/** 
 * variable frame rate. 
 */
private void writeVideoOnlyVFR(File movieFile,File[] imgFiles,int width,int height,double fps,QuickTimeWriter.VideoFormat videoFormat,String streaming) throws IOException {
  File tmpFile=streaming.equals("none") ? movieFile : new File(movieFile.getPath() + ".tmp");
  ProgressMonitor p=new ProgressMonitor(MovieMaker.this,Language.interpolate("movie_maker.progress.creating_file_name",movieFile.getName()),Language.text("movie_maker.progress.creating_output_file"),0,imgFiles.length);
  Graphics2D g=null;
  BufferedImage img=null;
  BufferedImage prevImg=null;
  int[] data=null;
  int[] prevData=null;
  QuickTimeWriter qtOut=null;
  try {
    int timeScale=(int)(fps * 100.0);
    int duration=100;
    qtOut=new QuickTimeWriter(videoFormat == QuickTimeWriter.VideoFormat.RAW ? movieFile : tmpFile);
    qtOut.addVideoTrack(videoFormat,timeScale,width,height);
    qtOut.setSyncInterval(0,30);
    if (true) {
      img=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
      data=((DataBufferInt)img.getRaster().getDataBuffer()).getData();
      prevImg=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
      prevData=((DataBufferInt)prevImg.getRaster().getDataBuffer()).getData();
      g=img.createGraphics();
      g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
    }
    int prevImgDuration=0;
    for (int i=0; i < imgFiles.length && !p.isCanceled(); i++) {
      File f=imgFiles[i];
      if (f == null)       continue;
      p.setNote(Language.interpolate("movie_maker.progress.processing",f.getName()));
      p.setProgress(i);
      if (false) {
        qtOut.writeSample(0,f,duration);
      }
 else {
        BufferedImage fImg=readImage(f);
        if (fImg == null)         continue;
        g.drawImage(fImg,0,0,width,height,null);
        if (i != 0 && Arrays.equals(data,prevData)) {
          prevImgDuration+=duration;
        }
 else {
          if (prevImgDuration != 0) {
            qtOut.writeFrame(0,prevImg,prevImgDuration);
          }
          prevImgDuration=duration;
          System.arraycopy(data,0,prevData,0,data.length);
        }
      }
    }
    if (prevImgDuration != 0) {
      qtOut.writeFrame(0,prevImg,prevImgDuration);
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
  finally {
    p.close();
    if (g != null) {
      g.dispose();
    }
    if (img != null) {
      img.flush();
    }
    if (qtOut != null) {
      qtOut.close();
    }
  }
}
