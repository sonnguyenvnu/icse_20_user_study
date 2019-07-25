private boolean probe(){
  try {
    FileTime nowLastModified=Files.getLastModifiedTime(file);
    FileTime previousLastModified=lastModifiedHolder.getAndSet(nowLastModified);
    return !nowLastModified.equals(previousLastModified);
  }
 catch (  Exception e) {
    throw uncheck(e);
  }
}
