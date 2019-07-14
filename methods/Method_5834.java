/** 
 * Start a new write operation on the file. This returns an  {@link OutputStream} to which you canwrite the new file data. If the whole data is written successfully you <em>must</em> call {@link #endWrite(OutputStream)}. On failure you should call  {@link OutputStream#close()}only to free up resources used by it. <p>Example usage: <pre> DataOutputStream dataOutput = null; try { OutputStream outputStream = atomicFile.startWrite(); dataOutput = new DataOutputStream(outputStream); // Wrapper stream dataOutput.write(data1); dataOutput.write(data2); atomicFile.endWrite(dataOutput); // Pass wrapper stream } finally{ if (dataOutput != null) { dataOutput.close(); } } </pre> <p>Note that if another thread is currently performing a write, this will simply replace whatever that thread is writing with the new file being written by this thread, and when the other thread finishes the write the new write operation will no longer be safe (or will be lost). You must do your own threading protection for access to AtomicFile.
 */
public OutputStream startWrite() throws IOException {
  if (baseName.exists()) {
    if (!backupName.exists()) {
      if (!baseName.renameTo(backupName)) {
        Log.w(TAG,"Couldn't rename file " + baseName + " to backup file " + backupName);
      }
    }
 else {
      baseName.delete();
    }
  }
  OutputStream str;
  try {
    str=new AtomicFileOutputStream(baseName);
  }
 catch (  FileNotFoundException e) {
    File parent=baseName.getParentFile();
    if (parent == null || !parent.mkdirs()) {
      throw new IOException("Couldn't create directory " + baseName,e);
    }
    try {
      str=new AtomicFileOutputStream(baseName);
    }
 catch (    FileNotFoundException e2) {
      throw new IOException("Couldn't create " + baseName,e2);
    }
  }
  return str;
}
