static public int launch(String zipPath) throws IOException {
  final ZipFile zip=new ZipFile(zipPath);
  final Map<String,ZipEntry> entries=new HashMap<String,ZipEntry>();
  Enumeration en=zip.entries();
  while (en.hasMoreElements()) {
    ZipEntry entry=(ZipEntry)en.nextElement();
    entries.put(entry.getName(),entry);
  }
  for (int i=0; i < workers; ++i) {
    WebServerWorker w=new WebServerWorker(zip,entries);
    Thread t=new Thread(w,"Web Server Worker #" + i);
    t.start();
    threads.addElement(w);
  }
  final int port=8080;
  Runnable r=new Runnable(){
    public void run(){
      try {
        ServerSocket ss=new ServerSocket(port);
        while (true) {
          Socket s=ss.accept();
          WebServerWorker w=null;
synchronized (threads) {
            if (threads.isEmpty()) {
              WebServerWorker ws=new WebServerWorker(zip,entries);
              ws.setSocket(s);
              (new Thread(ws,"additional worker")).start();
            }
 else {
              w=(WebServerWorker)threads.elementAt(0);
              threads.removeElementAt(0);
              w.setSocket(s);
            }
          }
        }
      }
 catch (      IOException e) {
        e.printStackTrace();
      }
    }
  }
;
  new Thread(r).start();
  return port;
}
