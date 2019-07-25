public void exec(String url,RequestConfig cfg,SSLEngine engine,IRespListener cb){
  URI uri, proxyUri=null;
  try {
    uri=new URI(url);
    if (cfg.proxy_url != null) {
      proxyUri=new URI(cfg.proxy_url);
    }
  }
 catch (  URISyntaxException e) {
    cb.onThrowable(e);
    return;
  }
  if (uri.getHost() == null) {
    cb.onThrowable(new IllegalArgumentException("host is null: " + url));
    return;
  }
  String scheme=uri.getScheme();
  if (!"http".equals(scheme) && !"https".equals(scheme)) {
    String message=(scheme == null) ? "No protocol specified" : scheme + " is not supported";
    cb.onThrowable(new ProtocolException(message));
    return;
  }
  InetSocketAddress addr;
  try {
    if (proxyUri == null) {
      addr=addressFinder.findAddress(uri);
    }
 else {
      addr=addressFinder.findAddress(proxyUri);
    }
  }
 catch (  UnknownHostException e) {
    cb.onThrowable(e);
    return;
  }
  HeaderMap headers=HeaderMap.camelCase(cfg.headers);
  if (!headers.containsKey("Host"))   headers.put("Host",HttpUtils.getHost(uri));
  if (!headers.containsKey("User-Agent"))   headers.put("User-Agent",RequestConfig.DEFAULT_USER_AGENT);
  if (!headers.containsKey("Accept-Encoding"))   headers.put("Accept-Encoding","gzip, deflate");
  ByteBuffer request[];
  try {
    if (proxyUri == null) {
      request=encode(cfg.method,headers,cfg.body,HttpUtils.getPath(uri));
    }
 else {
      String proxyScheme=proxyUri.getScheme();
      headers.put("Proxy-Connection","Keep-Alive");
      if (("http".equals(proxyScheme) && !"https".equals(scheme)) || cfg.tunnel == false) {
        request=encode(cfg.method,headers,cfg.body,uri.toString());
      }
 else       if ("https".equals(proxyScheme) || "https".equals(scheme)) {
        headers.put("Host",HttpUtils.getProxyHost(uri));
        headers.put("Protocol","https");
        HttpMethod https_method=cfg.tunnel == true ? HttpMethod.valueOf("CONNECT") : cfg.method;
        request=encode(https_method,headers,cfg.body,HttpUtils.getProxyHost(uri));
      }
 else {
        String message=(proxyScheme == null) ? "No proxy protocol specified" : proxyScheme + " for proxy is not supported";
        cb.onThrowable(new ProtocolException(message));
        return;
      }
    }
  }
 catch (  IOException e) {
    cb.onThrowable(e);
    return;
  }
  if ((proxyUri == null && "https".equals(scheme)) || (proxyUri != null && "https".equals(proxyUri.getScheme()))) {
    if (engine == null) {
      engine=DEFAULT_CONTEXT.createSSLEngine();
    }
    if (!engine.getUseClientMode())     engine.setUseClientMode(true);
    sslEngineUriConfigurer.configure(engine,uri);
    pending.offer(new HttpsRequest(addr,request,cb,requests,cfg,engine));
  }
 else {
    pending.offer(new Request(addr,request,cb,requests,cfg));
  }
  selector.wakeup();
}
