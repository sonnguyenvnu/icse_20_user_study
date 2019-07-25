/** 
 * Get a snapshot of the currently playing video. <p> This implementation uses the native libvlc method to save a snapshot of the currently playing video. This snapshot is saved to a temporary file and then the resultant image is loaded from the file. <p> If one of width or height is zero the original image aspect ratio will be preserved. <p> If both width and height are zero, the original image size will be used, see {@link #get()}<p> Taking a snapshot is an asynchronous function, the snapshot is not available until after the  {@link MediaPlayerEventListener#snapshotTaken(MediaPlayer,String)} eventis received.
 * @param width desired image width
 * @param height desired image height
 * @return snapshot image, or <code>null</code> if a snapshot could not be taken
 */
public BufferedImage get(int width,int height){
  File file=null;
  try {
    file=File.createTempFile("vlcj-snapshot-",".png");
    return ImageIO.read(new File(new WaitForSnapshot(mediaPlayer,file,width,height).await()));
  }
 catch (  IOException e) {
    throw new RuntimeException("Failed to get snapshot image",e);
  }
catch (  InterruptedException e) {
    throw new RuntimeException("Failed to get snapshot image",e);
  }
catch (  BeforeWaiterAbortedException e) {
    return null;
  }
 finally {
    if (file != null) {
      file.delete();
    }
  }
}
