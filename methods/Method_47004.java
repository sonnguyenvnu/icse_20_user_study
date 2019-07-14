@Override protected void extractWithFilter(@NonNull Filter filter) throws IOException {
  long totalBytes=0;
  List<ZipEntry> entriesToExtract=new ArrayList<>();
  ZipFile zipfile=new ZipFile(filePath);
  for (Enumeration<? extends ZipEntry> e=zipfile.entries(); e.hasMoreElements(); ) {
    ZipEntry zipEntry=e.nextElement();
    if (CompressedHelper.isEntryPathValid(zipEntry.getName())) {
      if (filter.shouldExtract(zipEntry.getName(),zipEntry.isDirectory())) {
        entriesToExtract.add(zipEntry);
        totalBytes+=zipEntry.getSize();
      }
    }
 else {
      invalidArchiveEntries.add(zipEntry.getName());
    }
  }
  listener.onStart(totalBytes,entriesToExtract.get(0).getName());
  for (  ZipEntry entry : entriesToExtract) {
    if (!listener.isCancelled()) {
      listener.onUpdate(entry.getName());
      extractEntry(context,zipfile,entry,outputPath);
    }
  }
  listener.onFinish();
}
