/** 
 * Compresses the given directory and all its sub-directories into a ZIP file.
 * @param dir root directory.
 * @param out ZIP output stream.
 * @param mapper call-back for renaming the entries.
 * @param pathPrefix prefix to be used for the entries.
 * @param mustHaveChildren if true, but directory to pack doesn't have any files, throw an exception.
 */
private static void pack(File dir,ZipOutputStream out,NameMapper mapper,String pathPrefix,boolean mustHaveChildren) throws IOException {
  String[] filenames=dir.list();
  if (filenames == null) {
    if (!dir.exists()) {
      throw new ZipException("Given file '" + dir + "' doesn't exist!");
    }
    throw new IOException("Given file is not a directory '" + dir + "'");
  }
  if (mustHaveChildren && filenames.length == 0) {
    throw new ZipException("Given directory '" + dir + "' doesn't contain any files!");
  }
  for (int i=0; i < filenames.length; i++) {
    String filename=filenames[i];
    File file=new File(dir,filename);
    boolean isDir=file.isDirectory();
    String path=pathPrefix + file.getName();
    if (isDir) {
      path+=PATH_SEPARATOR;
    }
    String name=mapper.map(path);
    if (name != null) {
      ZipEntry zipEntry=ZipEntryUtil.fromFile(name,file);
      out.putNextEntry(zipEntry);
      if (!isDir) {
        FileUtils.copy(file,out);
      }
      out.closeEntry();
    }
    if (isDir) {
      pack(file,out,mapper,path,false);
    }
  }
}
