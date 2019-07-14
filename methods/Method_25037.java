/** 
 * Serves file from homeDir and its' subdirectories (only). Uses only URI, ignores all headers and HTTP parameters.
 */
Response serveFile(String uri,Map<String,String> header,File file,String mime){
  Response res;
  try {
    String etag=Integer.toHexString((file.getAbsolutePath() + file.lastModified() + "" + file.length()).hashCode());
    long startFrom=0;
    long endAt=-1;
    String range=header.get("range");
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
 catch (        NumberFormatException ignored) {
        }
      }
    }
    String ifRange=header.get("if-range");
    boolean headerIfRangeMissingOrMatching=(ifRange == null || etag.equals(ifRange));
    String ifNoneMatch=header.get("if-none-match");
    boolean headerIfNoneMatchPresentAndMatching=ifNoneMatch != null && ("*".equals(ifNoneMatch) || ifNoneMatch.equals(etag));
    long fileLen=file.length();
    if (headerIfRangeMissingOrMatching && range != null && startFrom >= 0 && startFrom < fileLen) {
      if (headerIfNoneMatchPresentAndMatching) {
        res=newFixedLengthResponse(Status.NOT_MODIFIED,mime,"");
        res.addHeader("ETag",etag);
      }
 else {
        if (endAt < 0) {
          endAt=fileLen - 1;
        }
        long newLen=endAt - startFrom + 1;
        if (newLen < 0) {
          newLen=0;
        }
        FileInputStream fis=new FileInputStream(file);
        fis.skip(startFrom);
        res=Response.newFixedLengthResponse(Status.PARTIAL_CONTENT,mime,fis,newLen);
        res.addHeader("Accept-Ranges","bytes");
        res.addHeader("Content-Length","" + newLen);
        res.addHeader("Content-Range","bytes " + startFrom + "-" + endAt + "/" + fileLen);
        res.addHeader("ETag",etag);
      }
    }
 else {
      if (headerIfRangeMissingOrMatching && range != null && startFrom >= fileLen) {
        res=newFixedLengthResponse(Status.RANGE_NOT_SATISFIABLE,NanoHTTPD.MIME_PLAINTEXT,"");
        res.addHeader("Content-Range","bytes */" + fileLen);
        res.addHeader("ETag",etag);
      }
 else       if (range == null && headerIfNoneMatchPresentAndMatching) {
        res=newFixedLengthResponse(Status.NOT_MODIFIED,mime,"");
        res.addHeader("ETag",etag);
      }
 else       if (!headerIfRangeMissingOrMatching && headerIfNoneMatchPresentAndMatching) {
        res=newFixedLengthResponse(Status.NOT_MODIFIED,mime,"");
        res.addHeader("ETag",etag);
      }
 else {
        res=newFixedFileResponse(file,mime);
        res.addHeader("Content-Length","" + fileLen);
        res.addHeader("ETag",etag);
      }
    }
  }
 catch (  IOException ioe) {
    res=getForbiddenResponse("Reading file failed.");
  }
  return res;
}
