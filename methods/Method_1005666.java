@Override public InputStream open(String path) throws IOException {
  ZipEntry entry=archive.getEntry(path);
  if (entry == null) {
    throw new FileNotFoundException("File \"" + path + "\" not found");
  }
 else   if (entry.isDirectory()) {
    throw new DirectoryEntryException();
  }
 else {
    return archive.getInputStream(entry);
  }
}
