/** 
 * Lists zip content.
 */
public static List<String> listZip(final File zipFile) throws IOException {
  List<String> entries=new ArrayList<>();
  ZipFile zip=new ZipFile(zipFile);
  Enumeration zipEntries=zip.entries();
  while (zipEntries.hasMoreElements()) {
    ZipEntry entry=(ZipEntry)zipEntries.nextElement();
    String entryName=entry.getName();
    entries.add(entryName);
  }
  return Collections.unmodifiableList(entries);
}
