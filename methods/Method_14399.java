static public void retrieveContentFromPostRequest(HttpServletRequest request,Properties parameters,File rawDataDir,ObjectNode retrievalRecord,final Progress progress) throws Exception {
  ArrayNode fileRecords=ParsingUtilities.mapper.createArrayNode();
  JSONUtilities.safePut(retrievalRecord,"files",fileRecords);
  int clipboardCount=0;
  int uploadCount=0;
  int downloadCount=0;
  int archiveCount=0;
  final SavingUpdate update=new SavingUpdate(){
    @Override public void savedMore(){
      progress.setProgress(null,calculateProgressPercent(totalExpectedSize,totalRetrievedSize));
    }
    @Override public boolean isCanceled(){
      return progress.isCanceled();
    }
  }
;
  DiskFileItemFactory fileItemFactory=new DiskFileItemFactory();
  ServletFileUpload upload=new ServletFileUpload(fileItemFactory);
  upload.setProgressListener(new ProgressListener(){
    @Override public void update(    long bytesRead,    long contentLength,    int itemCount){
      if (!setContentLength) {
        if (contentLength >= 0) {
          update.totalExpectedSize+=contentLength;
          setContentLength=true;
        }
      }
      if (setContentLength) {
        update.totalRetrievedSize+=(bytesRead - lastBytesRead);
        lastBytesRead=bytesRead;
        update.savedMore();
      }
    }
  }
);
  @SuppressWarnings("unchecked") List<FileItem> tempFiles=(List<FileItem>)upload.parseRequest(request);
  progress.setProgress("Uploading data ...",-1);
  parts:   for (  FileItem fileItem : tempFiles) {
    if (progress.isCanceled()) {
      break;
    }
    InputStream stream=fileItem.getInputStream();
    String name=fileItem.getFieldName().toLowerCase();
    if (fileItem.isFormField()) {
      if (name.equals("clipboard")) {
        String encoding=request.getCharacterEncoding();
        if (encoding == null) {
          encoding="UTF-8";
        }
        File file=allocateFile(rawDataDir,"clipboard.txt");
        ObjectNode fileRecord=ParsingUtilities.mapper.createObjectNode();
        JSONUtilities.safePut(fileRecord,"origin","clipboard");
        JSONUtilities.safePut(fileRecord,"declaredEncoding",encoding);
        JSONUtilities.safePut(fileRecord,"declaredMimeType",(String)null);
        JSONUtilities.safePut(fileRecord,"format","text");
        JSONUtilities.safePut(fileRecord,"fileName","(clipboard)");
        JSONUtilities.safePut(fileRecord,"location",getRelativePath(file,rawDataDir));
        progress.setProgress("Uploading pasted clipboard text",calculateProgressPercent(update.totalExpectedSize,update.totalRetrievedSize));
        JSONUtilities.safePut(fileRecord,"size",saveStreamToFile(stream,file,null));
        JSONUtilities.append(fileRecords,fileRecord);
        clipboardCount++;
      }
 else       if (name.equals("download")) {
        String urlString=Streams.asString(stream);
        URL url=new URL(urlString);
        ObjectNode fileRecord=ParsingUtilities.mapper.createObjectNode();
        JSONUtilities.safePut(fileRecord,"origin","download");
        JSONUtilities.safePut(fileRecord,"url",urlString);
        for (        UrlRewriter rewriter : ImportingManager.urlRewriters) {
          Result result=rewriter.rewrite(urlString);
          if (result != null) {
            urlString=result.rewrittenUrl;
            url=new URL(urlString);
            JSONUtilities.safePut(fileRecord,"url",urlString);
            JSONUtilities.safePut(fileRecord,"format",result.format);
            if (!result.download) {
              downloadCount++;
              JSONUtilities.append(fileRecords,fileRecord);
              continue parts;
            }
          }
        }
        if ("http".equals(url.getProtocol()) || "https".equals(url.getProtocol())) {
          DefaultHttpClient client=new DefaultHttpClient();
          DecompressingHttpClient httpclient=new DecompressingHttpClient(client);
          HttpGet httpGet=new HttpGet(url.toURI());
          httpGet.setHeader("User-Agent",RefineServlet.getUserAgent());
          if ("https".equals(url.getProtocol())) {
            String userinfo=url.getUserInfo();
            if (userinfo != null) {
              int s=userinfo.indexOf(':');
              if (s > 0) {
                String user=userinfo.substring(0,s);
                String pw=userinfo.substring(s + 1,userinfo.length());
                client.getCredentialsProvider().setCredentials(new AuthScope(url.getHost(),443),new UsernamePasswordCredentials(user,pw));
              }
            }
          }
          HttpResponse response=httpclient.execute(httpGet);
          try {
            response.getStatusLine();
            HttpEntity entity=response.getEntity();
            if (entity == null) {
              throw new Exception("No content found in " + url.toString());
            }
            InputStream stream2=entity.getContent();
            String encoding=null;
            if (entity.getContentEncoding() != null) {
              encoding=entity.getContentEncoding().getValue();
            }
            JSONUtilities.safePut(fileRecord,"declaredEncoding",encoding);
            String contentType=null;
            if (entity.getContentType() != null) {
              contentType=entity.getContentType().getValue();
            }
            JSONUtilities.safePut(fileRecord,"declaredMimeType",contentType);
            if (saveStream(stream2,url,rawDataDir,progress,update,fileRecord,fileRecords,entity.getContentLength())) {
              archiveCount++;
            }
            downloadCount++;
            EntityUtils.consume(entity);
          }
  finally {
            httpGet.releaseConnection();
          }
        }
 else {
          URLConnection urlConnection=url.openConnection();
          urlConnection.setConnectTimeout(5000);
          urlConnection.connect();
          InputStream stream2=urlConnection.getInputStream();
          JSONUtilities.safePut(fileRecord,"declaredEncoding",urlConnection.getContentEncoding());
          JSONUtilities.safePut(fileRecord,"declaredMimeType",urlConnection.getContentType());
          try {
            if (saveStream(stream2,url,rawDataDir,progress,update,fileRecord,fileRecords,urlConnection.getContentLength())) {
              archiveCount++;
            }
            downloadCount++;
          }
  finally {
            stream2.close();
          }
        }
      }
 else {
        String value=Streams.asString(stream);
        parameters.put(name,value);
      }
    }
 else {
      String fileName=fileItem.getName();
      if (fileName.length() > 0) {
        long fileSize=fileItem.getSize();
        File file=allocateFile(rawDataDir,fileName);
        ObjectNode fileRecord=ParsingUtilities.mapper.createObjectNode();
        JSONUtilities.safePut(fileRecord,"origin","upload");
        JSONUtilities.safePut(fileRecord,"declaredEncoding",request.getCharacterEncoding());
        JSONUtilities.safePut(fileRecord,"declaredMimeType",fileItem.getContentType());
        JSONUtilities.safePut(fileRecord,"fileName",fileName);
        JSONUtilities.safePut(fileRecord,"location",getRelativePath(file,rawDataDir));
        progress.setProgress("Saving file " + fileName + " locally (" + formatBytes(fileSize) + " bytes)",calculateProgressPercent(update.totalExpectedSize,update.totalRetrievedSize));
        JSONUtilities.safePut(fileRecord,"size",saveStreamToFile(stream,file,null));
        if (postProcessRetrievedFile(rawDataDir,file,fileRecord,fileRecords,progress)) {
          archiveCount++;
        }
        uploadCount++;
      }
    }
    stream.close();
  }
  for (  FileItem fileItem : tempFiles) {
    fileItem.delete();
  }
  JSONUtilities.safePut(retrievalRecord,"uploadCount",uploadCount);
  JSONUtilities.safePut(retrievalRecord,"downloadCount",downloadCount);
  JSONUtilities.safePut(retrievalRecord,"clipboardCount",clipboardCount);
  JSONUtilities.safePut(retrievalRecord,"archiveCount",archiveCount);
}
