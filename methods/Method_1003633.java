/** 
 * Get checksum service for additional path related to server's base dir and calculation method as checksummer function. Apply file filtering by extension: <i>js</i>, <i>css</i>, <i>png</i> filter files in target path. If checksummer function is not provided then noop method is used (no checksum calculation). If additional path is not given then server's base dir is used. If additional path is not correct path definition (contains illegal characters) or is not existing directory, IllegalArgumentException is thrown. If fileEndsWith variable length argument is not given then checksum may be calculated for file with any extension. If fileEndsWith is given and file's extension no match NoSuchFileException is thrown.
 * @param serverConfig server configuration. The most important parameter is baseDir. File path taken as parameter to checksummer is calculated relative to baseDir and additional path.
 * @param checksummerFunc checksum calculation function
 * @param path additional path calculated relative to server's base dir. Becomes root for checksummer function.
 * @param fileEndsWith variable length array of extenstions filtering files in target path
 * @return file system checksummer service
 */
public static FileSystemChecksumService service(ServerConfig serverConfig,Function<? super InputStream,? extends String> checksummerFunc,String path,String... fileEndsWith){
  Function<? super InputStream,? extends String> checksummer=checksummerFunc != null ? checksummerFunc : noopChecksummer();
  FileSystemBinding fsb=path != null ? serverConfig.getBaseDir().binding(path) : serverConfig.getBaseDir();
  List<String> exts=Arrays.asList(fileEndsWith);
  if (fsb == null || !Files.isDirectory(fsb.getFile())) {
    throw new IllegalArgumentException("Non existing path related to server's base dir.");
  }
  DefaultFileSystemChecksumService service=new DefaultFileSystemChecksumService(fsb,checksummer,exts);
  if (serverConfig.isDevelopment()) {
    return service;
  }
 else {
    CachingFileSystemChecksumService cachingService=new CachingFileSystemChecksumService(service);
    new FileSystemChecksumServicePopulater(fsb.getFile(),exts,cachingService,Executors.newFixedThreadPool(5),4).start();
    return cachingService;
  }
}
