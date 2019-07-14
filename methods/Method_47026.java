/** 
 * Copy a resource file into a private target directory, if the target does not yet exist. Required for the Kitkat workaround.
 * @param resource   The resource file.
 * @param folderName The folder below app folder where the file is copied to.
 * @param targetName The name of the target file.
 * @return the dummy file.
 */
private static File copyDummyFile(final int resource,final String folderName,final String targetName,Context context) throws IOException {
  File externalFilesDir=context.getExternalFilesDir(folderName);
  if (externalFilesDir == null) {
    return null;
  }
  File targetFile=new File(externalFilesDir,targetName);
  if (!targetFile.exists()) {
    InputStream in=null;
    OutputStream out=null;
    try {
      in=context.getResources().openRawResource(resource);
      out=new FileOutputStream(targetFile);
      byte[] buffer=new byte[4096];
      int bytesRead;
      while ((bytesRead=in.read(buffer)) != -1) {
        out.write(buffer,0,bytesRead);
      }
    }
  finally {
      if (in != null) {
        try {
          in.close();
        }
 catch (        IOException ex) {
        }
      }
      if (out != null) {
        try {
          out.close();
        }
 catch (        IOException ex) {
        }
      }
    }
  }
  return targetFile;
}
