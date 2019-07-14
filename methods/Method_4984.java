/** 
 * Loads  {@link DownloadAction}s from file.
 * @return Loaded DownloadActions. If the action file doesn't exists returns an empty array.
 * @throws IOException If there is an error during loading.
 */
public DownloadAction[] load() throws IOException {
  if (!actionFile.exists()) {
    return new DownloadAction[0];
  }
  InputStream inputStream=null;
  try {
    inputStream=atomicFile.openRead();
    DataInputStream dataInputStream=new DataInputStream(inputStream);
    int version=dataInputStream.readInt();
    if (version > VERSION) {
      throw new IOException("Unsupported action file version: " + version);
    }
    int actionCount=dataInputStream.readInt();
    DownloadAction[] actions=new DownloadAction[actionCount];
    for (int i=0; i < actionCount; i++) {
      actions[i]=DownloadAction.deserializeFromStream(dataInputStream);
    }
    return actions;
  }
  finally {
    Util.closeQuietly(inputStream);
  }
}
