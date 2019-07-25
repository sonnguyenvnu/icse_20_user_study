/** 
 * Appends mp4 audio/video from  {@code anotherFileName} to{@code mainFileName}.
 */
public static File append(File[] inputFiles){
  try {
    File targetFile=inputFiles[0];
    int[] inputFilesFds=new int[inputFiles.length];
    ArrayList<ParcelFileDescriptor> pfdsList=new ArrayList<ParcelFileDescriptor>();
    int i=0;
    for (    File f : inputFiles) {
      ParcelFileDescriptor pfd=ParcelFileDescriptor.open(f,ParcelFileDescriptor.MODE_READ_WRITE);
      pfdsList.add(pfd);
      inputFilesFds[i]=pfd.getFd();
      i++;
    }
    if (targetFile.exists() && targetFile.length() > 0) {
      File tmpTargetFile=new File(targetFile.getAbsolutePath() + ".tmp");
      ParcelFileDescriptor targetFilePfd=ParcelFileDescriptor.open(tmpTargetFile,ParcelFileDescriptor.MODE_CREATE | ParcelFileDescriptor.MODE_READ_WRITE);
      Mp4Editor.appendFds(inputFilesFds,targetFilePfd.getFd());
      targetFilePfd.close();
      for (      ParcelFileDescriptor pfd : pfdsList) {
        pfd.close();
      }
      return tmpTargetFile;
    }
 else     if (targetFile.createNewFile()) {
      copyFile(inputFiles[1].getAbsolutePath(),inputFiles[0].getAbsolutePath());
      return targetFile;
    }
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
  return null;
}
