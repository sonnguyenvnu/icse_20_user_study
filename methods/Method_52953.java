@Override public HttpResponse handleRequest(HttpRequest req) throws TwitterException {
  HTTPRequest request;
  try {
    request=new HTTPRequest(new URL(req.getURL()),HTTPMethod.valueOf(req.getMethod().name()),Builder.disallowTruncate().setDeadline(CONF.getHttpReadTimeout() / 1000D));
  }
 catch (  MalformedURLException e) {
    throw new TwitterException(e);
  }
  int responseCode=-1;
  ByteArrayOutputStream os;
  try {
    setHeaders(req,request);
    if (req.getMethod() == POST) {
      if (HttpParameter.containsFile(req.getParameters())) {
        String boundary="----Twitter4J-upload" + System.currentTimeMillis();
        request.setHeader(new HTTPHeader("Content-Type","multipart/form-data; boundary=" + boundary));
        boundary="--" + boundary;
        os=new ByteArrayOutputStream();
        DataOutputStream out=new DataOutputStream(os);
        for (        HttpParameter param : req.getParameters()) {
          if (param.isFile()) {
            write(out,boundary + "\r\n");
            write(out,"Content-Disposition: form-data; name=\"" + param.getName() + "\"; filename=\"" + param.getFile().getName() + "\"\r\n");
            write(out,"Content-Type: " + param.getContentType() + "\r\n\r\n");
            BufferedInputStream in=new BufferedInputStream(param.hasFileBody() ? param.getFileBody() : new FileInputStream(param.getFile()));
            int buff=0;
            while ((buff=in.read()) != -1) {
              out.write(buff);
            }
            write(out,"\r\n");
            in.close();
          }
 else {
            write(out,boundary + "\r\n");
            write(out,"Content-Disposition: form-data; name=\"" + param.getName() + "\"\r\n");
            write(out,"Content-Type: text/plain; charset=UTF-8\r\n\r\n");
            logger.debug(param.getValue());
            out.write(param.getValue().getBytes("UTF-8"));
            write(out,"\r\n");
          }
        }
        write(out,boundary + "--\r\n");
        write(out,"\r\n");
      }
 else {
        String postParam;
        if (HttpParameter.containsJson(req.getParameters())) {
          request.setHeader(new HTTPHeader("Content-Type","application/json"));
          postParam=req.getParameters()[0].getJsonObject().toString();
        }
 else {
          request.setHeader(new HTTPHeader("Content-Type","application/x-www-form-urlencoded"));
          postParam=HttpParameter.encodeParameters(req.getParameters());
        }
        logger.debug("Post Params: ",postParam);
        byte[] bytes=postParam.getBytes("UTF-8");
        request.setHeader(new HTTPHeader("Content-Length",Integer.toString(bytes.length)));
        os=new ByteArrayOutputStream();
        os.write(bytes);
      }
      request.setPayload(os.toByteArray());
    }
    URLFetchService service=URLFetchServiceFactory.getURLFetchService();
    return new AppEngineHttpResponseImpl(service.fetchAsync(request));
  }
 catch (  IOException ioe) {
    throw new TwitterException(ioe.getMessage(),ioe,responseCode);
  }
}
