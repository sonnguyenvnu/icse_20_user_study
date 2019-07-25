public void authorize(HttpURLConnection httpCon){
  httpCon.setRequestProperty("Proxy-Authorization","Basic " + new String(Base64.encodeBase64(new String(config.getProxyUser() + ":" + config.getProxyPassword()).getBytes())).trim());
}
