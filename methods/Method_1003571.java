@Override public boolean replace(@NonNull StandardSession session) throws IOException {
  Assert.notNull(session,"The session can not be null.");
  String id=session.getId();
  if (StringUtils.isEmpty(id))   throw new IllegalStateException("The session id can not be empty or null.");
  ObjectOutputStream writer=null;
  try {
    if (!IOUtils.createFolder(mDirectory))     return false;
    File file=new File(mDirectory,id);
    if (!IOUtils.createNewFile(file))     return false;
    writer=new ObjectOutputStream(new FileOutputStream(file));
    session.writeObject(writer);
    return true;
  }
 catch (  IOException e) {
    IOUtils.delFileOrFolder(new File(mDirectory,id));
    throw e;
  }
 finally {
    IOUtils.closeQuietly(writer);
  }
}
