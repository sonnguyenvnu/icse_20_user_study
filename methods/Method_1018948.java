/** 
 * Save a snapshot of the currently playing video. <p> Any missing directory path will be created if it does not exist. <p> If one of width or height is zero the original image aspect ratio will be preserved. <p> If both width and height are zero, the original image size will be used, see {@link #save(File)}. <p> Taking a snapshot is an asynchronous function, the snapshot is not available until after the  {@link MediaPlayerEventListener#snapshotTaken(MediaPlayer,String)} eventis received.
 * @param file file to contain the snapshot
 * @param width desired image width
 * @param height desired image height
 * @return <code>true</code> if the snapshot was saved, otherwise <code>false</code>
 */
public boolean save(File file,int width,int height){
  File snapshotDirectory=file.getParentFile();
  if (snapshotDirectory == null) {
    snapshotDirectory=new File(".");
  }
  if (!snapshotDirectory.exists()) {
    snapshotDirectory.mkdirs();
  }
  if (snapshotDirectory.exists()) {
    return libvlc_video_take_snapshot(mediaPlayerInstance,0,file.getAbsolutePath(),width,height) == 0;
  }
 else {
    throw new RuntimeException("Directory does not exist and could not be created for '" + file.getAbsolutePath() + "'");
  }
}
