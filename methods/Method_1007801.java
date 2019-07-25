@Override public void save(UUID id,LocalSession session) throws IOException {
  File finalFile=getPath(id);
  File tempFile=new File(finalFile.getParentFile(),finalFile.getName() + ".tmp");
  try (Closer closer=Closer.create()){
    FileWriter fr=closer.register(new FileWriter(tempFile));
    BufferedWriter bw=closer.register(new BufferedWriter(fr));
    gson.toJson(session,bw);
  }
 catch (  JsonIOException e) {
    throw new IOException(e);
  }
  if (finalFile.exists()) {
    if (!finalFile.delete()) {
      log.warn("Failed to delete " + finalFile.getPath() + " so the .tmp file can replace it");
    }
  }
  if (!tempFile.renameTo(finalFile)) {
    log.warn("Failed to rename temporary session file to " + finalFile.getPath());
  }
}
