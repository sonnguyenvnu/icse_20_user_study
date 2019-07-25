public static boolean mux(String videoFile,String audioFile,String outputFile){
  com.googlecode.mp4parser.authoring.Movie video;
  try {
    new MovieCreator();
    video=MovieCreator.build(videoFile);
  }
 catch (  RuntimeException e) {
    e.printStackTrace();
    return false;
  }
catch (  IOException e) {
    e.printStackTrace();
    return false;
  }
  com.googlecode.mp4parser.authoring.Movie audio;
  try {
    new MovieCreator();
    audio=MovieCreator.build(audioFile);
  }
 catch (  IOException e) {
    e.printStackTrace();
    return false;
  }
catch (  NullPointerException e) {
    e.printStackTrace();
    return false;
  }
  com.googlecode.mp4parser.authoring.Track audioTrack=audio.getTracks().get(0);
  CroppedTrack croppedTrack=new CroppedTrack(audioTrack,0,audioTrack.getSamples().size());
  video.addTrack(croppedTrack);
  Container out=new DefaultMp4Builder().build(video);
  FileOutputStream fos;
  try {
    fos=new FileOutputStream(outputFile);
  }
 catch (  FileNotFoundException e) {
    e.printStackTrace();
    return false;
  }
  BufferedWritableFileByteChannel byteBufferByteChannel=new BufferedWritableFileByteChannel(fos);
  try {
    out.writeContainer(byteBufferByteChannel);
    byteBufferByteChannel.close();
    fos.close();
  }
 catch (  IOException e) {
    e.printStackTrace();
    return false;
  }
  return true;
}
