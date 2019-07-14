private String append(String base,Map<String,?> query,Map<String,String> keys,boolean fragment){
  UriComponentsBuilder template=UriComponentsBuilder.newInstance();
  UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(base);
  URI redirectUri;
  try {
    redirectUri=builder.build(true).toUri();
  }
 catch (  Exception e) {
    redirectUri=builder.build().toUri();
    builder=UriComponentsBuilder.fromUri(redirectUri);
  }
  template.scheme(redirectUri.getScheme()).port(redirectUri.getPort()).host(redirectUri.getHost()).userInfo(redirectUri.getUserInfo()).path(redirectUri.getPath());
  if (fragment) {
    StringBuilder values=new StringBuilder();
    if (redirectUri.getFragment() != null) {
      String append=redirectUri.getFragment();
      values.append(append);
    }
    for (    String key : query.keySet()) {
      if (values.length() > 0) {
        values.append("&");
      }
      String name=key;
      if (keys != null && keys.containsKey(key)) {
        name=keys.get(key);
      }
      values.append(name + "={" + key + "}");
    }
    if (values.length() > 0) {
      template.fragment(values.toString());
    }
    UriComponents encoded=template.build().expand(query).encode();
    builder.fragment(encoded.getFragment());
  }
 else {
    for (    String key : query.keySet()) {
      String name=key;
      if (keys != null && keys.containsKey(key)) {
        name=keys.get(key);
      }
      template.queryParam(name,"{" + key + "}");
    }
    template.fragment(redirectUri.getFragment());
    UriComponents encoded=template.build().expand(query).encode();
    builder.query(encoded.getQuery());
  }
  return builder.build().toUriString();
}
