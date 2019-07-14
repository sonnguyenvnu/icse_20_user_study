/** 
 * Stores  {@link DownloadAction}s to file.
 * @param downloadActions DownloadActions to store to file.
 * @throws IOException If there is an error during storing.
 */
public void store(DownloadAction... downloadActions) throws IOException {
  DataOutputStream output=null;
  try {
    output=new DataOutputStream(atomicFile.startWrite());
    output.writeInt(VERSION);
    output.writeInt(downloadActions.length);
    for (    DownloadAction action : downloadActions) {
      action.serializeToStream(output);
    }
    atomicFile.endWrite(output);
    output=null;
  }
  finally {
    Util.closeQuietly(output);
  }
}
