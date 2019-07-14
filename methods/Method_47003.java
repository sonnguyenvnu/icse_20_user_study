@Override protected void extractWithFilter(@NonNull Filter filter) throws IOException {
  try {
    long totalBytes=0;
    Archive rarFile=new Archive(new File(filePath));
    ArrayList<FileHeader> arrayList=new ArrayList<>();
    for (    FileHeader header : rarFile.getFileHeaders()) {
      if (CompressedHelper.isEntryPathValid(header.getFileNameString())) {
        if (filter.shouldExtract(header.getFileNameString(),header.isDirectory())) {
          arrayList.add(header);
          totalBytes+=header.getFullUnpackSize();
        }
      }
 else {
        invalidArchiveEntries.add(header.getFileNameString());
      }
    }
    listener.onStart(totalBytes,arrayList.get(0).getFileNameString());
    for (    FileHeader entry : arrayList) {
      if (!listener.isCancelled()) {
        listener.onUpdate(entry.getFileNameString());
        extractEntry(context,rarFile,entry,outputPath);
      }
    }
    listener.onFinish();
  }
 catch (  RarException e) {
    throw new IOException(e);
  }
}
