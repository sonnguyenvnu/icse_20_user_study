private Path unzip(Path zip,Path pluginsDir) throws IOException, UserException {
  final Path target=stagingDirectory(pluginsDir);
  pathsToDeleteOnShutdown.add(target);
  boolean hasEsDir=false;
  try (ZipInputStream zipInput=new ZipInputStream(Files.newInputStream(zip))){
    ZipEntry entry;
    byte[] buffer=new byte[8192];
    while ((entry=zipInput.getNextEntry()) != null) {
      if (entry.getName().startsWith("elasticsearch/") == false) {
        continue;
      }
      hasEsDir=true;
      Path targetFile=target.resolve(entry.getName().substring("elasticsearch/".length()));
      if (targetFile.normalize().startsWith(target) == false) {
        throw new UserException(PLUGIN_MALFORMED,"Zip contains entry name '" + entry.getName() + "' resolving outside of plugin directory");
      }
      if (!Files.isSymbolicLink(targetFile.getParent())) {
        Files.createDirectories(targetFile.getParent());
      }
      if (entry.isDirectory() == false) {
        try (OutputStream out=Files.newOutputStream(targetFile)){
          int len;
          while ((len=zipInput.read(buffer)) >= 0) {
            out.write(buffer,0,len);
          }
        }
       }
      zipInput.closeEntry();
    }
  }
   Files.delete(zip);
  if (hasEsDir == false) {
    IOUtils.rm(target);
    throw new UserException(PLUGIN_MALFORMED,"`elasticsearch` directory is missing in the plugin zip");
  }
  return target;
}
