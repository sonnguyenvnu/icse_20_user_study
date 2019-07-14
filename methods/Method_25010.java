protected long sendContentLengthHeaderIfNotAlreadyPresent(PrintWriter pw,long defaultSize){
  String contentLengthString=getHeader("content-length");
  long size=defaultSize;
  if (contentLengthString != null) {
    try {
      size=Long.parseLong(contentLengthString);
    }
 catch (    NumberFormatException ex) {
      NanoHTTPD.LOG.severe("content-length was no number " + contentLengthString);
    }
  }
 else {
    pw.print("Content-Length: " + size + "\r\n");
  }
  return size;
}
