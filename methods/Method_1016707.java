/** 
 * Asynchronously generate a list of all files on a ftp server using the anonymous account.
 * @param host host name or address
 * @param port ftp port
 * @param user user name
 * @param pw user password
 * @param path path on the ftp site
 * @param depth the maximum depth of the sub folders exploration.
 * @return a queue asynchronously filled with entryInfo from all files of the ftp server
 * @throws IOException when a error occurred
 */
public static BlockingQueue<entryInfo> sitelist(final String host,final int port,final String user,final String pw,final String path,final int depth) throws IOException {
  final FTPClient ftpClient=new FTPClient();
  ftpClient.open(host,port);
  ftpClient.login(user,pw);
  final LinkedBlockingQueue<entryInfo> queue=new LinkedBlockingQueue<entryInfo>();
  new Thread(){
    @Override public void run(){
      try {
        Thread.currentThread().setName("FTP.sitelist(" + host + ":" + port + ")");
        sitelist(ftpClient,path,queue,depth);
        ftpClient.quit();
      }
 catch (      final Exception e) {
      }
 finally {
        queue.add(POISON_entryInfo);
      }
    }
  }
.start();
  return queue;
}
