@Override public boolean exists(){
  try {
    String entryName=getEntryName();
    if (entryName.isEmpty()) {
      return true;
    }
    try (ZipFile file=openZipFile()){
      return file.getEntry(entryName) != null;
    }
   }
 catch (  IOException e) {
    return false;
  }
}
