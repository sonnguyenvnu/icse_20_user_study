/** 
 * Returns a path to a file that can be written. Tries multiple locations and verifies writing succeeds.
 * @param filename the resource filename
 * @return the extracted library
 */
private static Path getExtractPath(String filename,URL resource){
  String override=Configuration.SHARED_LIBRARY_EXTRACT_PATH.get();
  if (override != null) {
    return Paths.get(override,filename);
  }
  String version=Version.getVersion().replace(' ','-');
  Path tempDirectory;
  Path root, file;
  tempDirectory=Paths.get(Configuration.SHARED_LIBRARY_EXTRACT_DIRECTORY.get("lwjgl" + System.getProperty("user.name")),version,filename);
  file=(root=Paths.get(System.getProperty("java.io.tmpdir"))).resolve(tempDirectory);
  if (canWrite(root,file,resource)) {
    return file;
  }
  tempDirectory=Paths.get(Configuration.SHARED_LIBRARY_EXTRACT_DIRECTORY.get("lwjgl"),version,filename);
  file=(root=Paths.get(System.getProperty("user.home"))).resolve(tempDirectory);
  if (canWrite(root,file,resource)) {
    return file;
  }
  file=(root=Paths.get("").toAbsolutePath()).resolve(tempDirectory);
  if (canWrite(root,file,resource)) {
    return file;
  }
  if (Platform.get() == Platform.WINDOWS) {
    String env=System.getenv("SystemRoot");
    if (env != null) {
      file=(root=Paths.get(env,"Temp")).resolve(tempDirectory);
      if (canWrite(root,file,resource)) {
        return file;
      }
    }
    env=System.getenv("SystemDrive");
    if (env != null) {
      file=(root=Paths.get(env + "/")).resolve(Paths.get("Temp").resolve(tempDirectory));
      if (canWrite(root,file,resource)) {
        return file;
      }
    }
  }
  try {
    file=Files.createTempDirectory("lwjgl");
    root=file.getParent();
    file=file.resolve(filename);
    if (canWrite(root,file,resource)) {
      return file;
    }
  }
 catch (  IOException ignored) {
  }
  throw new RuntimeException("Failed to find an appropriate directory to extract the native library");
}
