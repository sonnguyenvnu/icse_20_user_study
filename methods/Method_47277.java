@Override public CloudStreamServer.Response serve(String uri,String method,Properties header,Properties parms,Properties files){
  CloudStreamServer.Response res=null;
  if (inputStream == null)   res=new CloudStreamServer.Response(HTTP_NOTFOUND,MIME_PLAINTEXT,null);
 else {
    long startFrom=0;
    long endAt=-1;
    String range=header.getProperty("range");
    if (range != null) {
      if (range.startsWith("bytes=")) {
        range=range.substring("bytes=".length());
        int minus=range.indexOf('-');
        try {
          if (minus > 0) {
            startFrom=Long.parseLong(range.substring(0,minus));
            endAt=Long.parseLong(range.substring(minus + 1));
          }
        }
 catch (        NumberFormatException nfe) {
        }
      }
    }
    Log.d(CloudUtil.TAG,"Request: " + range + " from: " + startFrom + ", to: " + endAt);
    final CloudStreamSource source=new CloudStreamSource(fileName,length,inputStream);
    long fileLen=source.length();
    if (range != null && startFrom > 0) {
      if (startFrom >= fileLen) {
        res=new CloudStreamServer.Response(HTTP_RANGE_NOT_SATISFIABLE,MIME_PLAINTEXT,null);
        res.addHeader("Content-Range","bytes 0-0/" + fileLen);
      }
 else {
        if (endAt < 0)         endAt=fileLen - 1;
        long newLen=fileLen - startFrom;
        if (newLen < 0)         newLen=0;
        Log.d(CloudUtil.TAG,"start=" + startFrom + ", endAt=" + endAt + ", newLen=" + newLen);
        final long dataLen=newLen;
        source.moveTo(startFrom);
        Log.d(CloudUtil.TAG,"Skipped " + startFrom + " bytes");
        res=new CloudStreamServer.Response(HTTP_PARTIALCONTENT,null,source);
        res.addHeader("Content-length","" + dataLen);
      }
    }
 else {
      source.reset();
      res=new CloudStreamServer.Response(HTTP_OK,null,source);
      res.addHeader("Content-Length","" + fileLen);
    }
  }
  res.addHeader("Accept-Ranges","bytes");
  return res;
}
