@Override public void export(BibDatabaseContext databaseContext,Path file,Charset encoding,List<BibEntry> entries) throws Exception {
  Objects.requireNonNull(databaseContext);
  Objects.requireNonNull(file);
  Objects.requireNonNull(entries);
  if (entries.isEmpty()) {
    return;
  }
  if (file.getFileName().toString().trim().equals(XMP_SPLIT_PATTERN)) {
    for (    BibEntry entry : entries) {
      Path entryFile;
      String suffix=entry.getId() + "_" + entry.getCiteKey() + ".xmp";
      if (file.getParent() == null) {
        entryFile=Paths.get(suffix);
      }
 else {
        entryFile=Paths.get(file.getParent().toString() + "/" + suffix);
      }
      this.writeBibToXmp(entryFile,Arrays.asList(entry),encoding);
    }
  }
 else {
    this.writeBibToXmp(file,entries,encoding);
  }
}
