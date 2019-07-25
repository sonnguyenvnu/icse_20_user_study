/** 
 * Feed the queue with files under a given path on a ftp server using the anonymous account. When path is a file path, only one entry is added to the queue.
 * @param ftpClient fptClient initialized with a host and login information
 * @param path path on the host
 * @param queue the entries queue to feed
 * @param depth the maximum depth of the sub folders exploration.
 * @throws IOException when a error occurred
 */
private static void sitelist(final FTPClient ftpClient,String path,final LinkedBlockingQueue<entryInfo> queue,int depth){
  List<String> list;
  try {
    list=ftpClient.list(path,true);
  }
 catch (  final IOException e) {
    if (!path.endsWith("/")) {
      entryInfo info=ftpClient.fileInfo(path);
      if (info != null) {
        queue.add(info);
      }
 else {
        info=new entryInfo();
        info.name=path;
        queue.add(info);
      }
    }
 else {
      Data.logger.warn("cannot make sitelist",e);
    }
    return;
  }
  if (!path.endsWith("/"))   path+="/";
  entryInfo info;
  for (  final String line : list) {
    info=parseListData(line);
    if (info != null && info.type == filetype.file && !info.name.endsWith(".") && !info.name.startsWith(".")) {
      if (!info.name.startsWith("/"))       info.name=path + info.name;
      queue.add(info);
    }
  }
  if (depth > 0) {
    for (    final String line : list) {
      info=parseListData(line);
      if (info != null && !info.name.endsWith(".") && !info.name.startsWith(".")) {
        if (info.type == filetype.directory) {
          sitelist(ftpClient,path + info.name,queue,depth - 1);
        }
 else         if (info.type == filetype.link) {
          final int q=info.name.indexOf("->",0);
          if (q >= 0 && info.name.indexOf("..",q) < 0) {
            info.name=info.name.substring(0,q).trim();
            sitelist(ftpClient,path + info.name,queue,depth - 1);
          }
        }
      }
    }
  }
}
