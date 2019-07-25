public static void main(String args[]) throws Exception {
  String s="GET /favicon.ico#test HTTP/1.1\r\n" + "Host: localhost:7262\r\n" + "User-Agent: Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.5; en-US; rv:1.9.0.10) Gecko/2009042315 Firefox/3.0.10 Ubiquity/0.1.5\r\n" + "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n" + "Accept-Language: en-us,en;q=0.5\r\n" + "Accept-Encoding: gzip,deflate\r\n" + "Accept-Charset: ISO-8859-1,utf-8;q=0.7,*;q=0.7\r\n" + "Keep-Alive: 300\r\n" + "Connection: keep-alive\r\n\r\n";
  System.out.println("Input Request: (" + s.length() + " bytes)");
  System.out.println(s);
  byte[] data=s.getBytes();
  int len=data.length;
  System.out.println("=============================================================");
  HttpRequest req=new HttpRequest();
  req.buffer=ByteBuffer.allocate(2048);
  req.buffer.put(data);
  initHeader(req,len);
  System.out.println(req);
}
