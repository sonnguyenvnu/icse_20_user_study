/** 
 * Decodes the Multipart Body data and put it into Key/Value pairs.
 */
private void decodeMultipartFormData(ContentType contentType,ByteBuffer fbuf,Map<String,List<String>> parms,Map<String,String> files) throws ResponseException {
  int pcount=0;
  try {
    int[] boundaryIdxs=getBoundaryPositions(fbuf,contentType.getBoundary().getBytes());
    if (boundaryIdxs.length < 2) {
      throw new ResponseException(Status.BAD_REQUEST,"BAD REQUEST: Content type is multipart/form-data but contains less than two boundary strings.");
    }
    byte[] partHeaderBuff=new byte[MAX_HEADER_SIZE];
    for (int boundaryIdx=0; boundaryIdx < boundaryIdxs.length - 1; boundaryIdx++) {
      fbuf.position(boundaryIdxs[boundaryIdx]);
      int len=(fbuf.remaining() < MAX_HEADER_SIZE) ? fbuf.remaining() : MAX_HEADER_SIZE;
      fbuf.get(partHeaderBuff,0,len);
      BufferedReader in=new BufferedReader(new InputStreamReader(new ByteArrayInputStream(partHeaderBuff,0,len),Charset.forName(contentType.getEncoding())),len);
      int headerLines=0;
      String mpline=in.readLine();
      headerLines++;
      if (mpline == null || !mpline.contains(contentType.getBoundary())) {
        throw new ResponseException(Status.BAD_REQUEST,"BAD REQUEST: Content type is multipart/form-data but chunk does not start with boundary.");
      }
      String partName=null, fileName=null, partContentType=null;
      mpline=in.readLine();
      headerLines++;
      while (mpline != null && mpline.trim().length() > 0) {
        Matcher matcher=NanoHTTPD.CONTENT_DISPOSITION_PATTERN.matcher(mpline);
        if (matcher.matches()) {
          String attributeString=matcher.group(2);
          matcher=NanoHTTPD.CONTENT_DISPOSITION_ATTRIBUTE_PATTERN.matcher(attributeString);
          while (matcher.find()) {
            String key=matcher.group(1);
            if ("name".equalsIgnoreCase(key)) {
              partName=matcher.group(2);
            }
 else             if ("filename".equalsIgnoreCase(key)) {
              fileName=matcher.group(2);
              if (!fileName.isEmpty()) {
                if (pcount > 0)                 partName=partName + String.valueOf(pcount++);
 else                 pcount++;
              }
            }
          }
        }
        matcher=NanoHTTPD.CONTENT_TYPE_PATTERN.matcher(mpline);
        if (matcher.matches()) {
          partContentType=matcher.group(2).trim();
        }
        mpline=in.readLine();
        headerLines++;
      }
      int partHeaderLength=0;
      while (headerLines-- > 0) {
        partHeaderLength=scipOverNewLine(partHeaderBuff,partHeaderLength);
      }
      if (partHeaderLength >= len - 4) {
        throw new ResponseException(Status.INTERNAL_ERROR,"Multipart header size exceeds MAX_HEADER_SIZE.");
      }
      int partDataStart=boundaryIdxs[boundaryIdx] + partHeaderLength;
      int partDataEnd=boundaryIdxs[boundaryIdx + 1] - 4;
      fbuf.position(partDataStart);
      List<String> values=parms.get(partName);
      if (values == null) {
        values=new ArrayList<String>();
        parms.put(partName,values);
      }
      if (partContentType == null) {
        byte[] data_bytes=new byte[partDataEnd - partDataStart];
        fbuf.get(data_bytes);
        values.add(new String(data_bytes,contentType.getEncoding()));
      }
 else {
        String path=saveTmpFile(fbuf,partDataStart,partDataEnd - partDataStart,fileName);
        if (!files.containsKey(partName)) {
          files.put(partName,path);
        }
 else {
          int count=2;
          while (files.containsKey(partName + count)) {
            count++;
          }
          files.put(partName + count,path);
        }
        values.add(fileName);
      }
    }
  }
 catch (  ResponseException re) {
    throw re;
  }
catch (  Exception e) {
    throw new ResponseException(Status.INTERNAL_ERROR,e.toString());
  }
}
