/** 
 * Extracts zip file to the target directory. If patterns are provided only matched paths are extracted.
 * @param zipFile zip file
 * @param destDir destination directory
 * @param patterns optional wildcard patterns of files to extract, may be <code>null</code>
 */
public static void unzip(final File zipFile,final File destDir,final String... patterns) throws IOException {
  ZipFile zip=new ZipFile(zipFile);
  Enumeration zipEntries=zip.entries();
  while (zipEntries.hasMoreElements()) {
    ZipEntry entry=(ZipEntry)zipEntries.nextElement();
    String entryName=entry.getName();
    if (patterns != null && patterns.length > 0) {
      if (Wildcard.matchPathOne(entryName,patterns) == -1) {
        continue;
      }
    }
    final File file=(destDir != null) ? new File(destDir,entryName) : new File(entryName);
    final File rootDir=destDir != null ? destDir : new File(".");
    if (!FileUtil.isAncestor(rootDir,file,true)) {
      throw new IOException("Unzipping");
    }
    if (entry.isDirectory()) {
      if (!file.mkdirs()) {
        if (!file.isDirectory()) {
          throw new IOException("Failed to create directory: " + file);
        }
      }
    }
 else {
      File parent=file.getParentFile();
      if (parent != null && !parent.exists()) {
        if (!parent.mkdirs()) {
          if (!file.isDirectory()) {
            throw new IOException("Failed to create directory: " + parent);
          }
        }
      }
      InputStream in=zip.getInputStream(entry);
      OutputStream out=null;
      try {
        out=new FileOutputStream(file);
        StreamUtil.copy(in,out);
      }
  finally {
        StreamUtil.close(out);
        StreamUtil.close(in);
      }
    }
  }
  close(zip);
}
