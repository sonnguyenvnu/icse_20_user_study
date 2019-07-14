protected URI appendQueryParameter(URI uri,OAuth2AccessToken accessToken){
  try {
    String query=uri.getRawQuery();
    String queryFragment=resource.getTokenName() + "=" + URLEncoder.encode(accessToken.getValue(),"UTF-8");
    if (query == null) {
      query=queryFragment;
    }
 else {
      query=query + "&" + queryFragment;
    }
    URI update=new URI(uri.getScheme(),uri.getUserInfo(),uri.getHost(),uri.getPort(),uri.getPath(),null,null);
    StringBuffer sb=new StringBuffer(update.toString());
    sb.append("?");
    sb.append(query);
    if (uri.getFragment() != null) {
      sb.append("#");
      sb.append(uri.getFragment());
    }
    return new URI(sb.toString());
  }
 catch (  URISyntaxException e) {
    throw new IllegalArgumentException("Could not parse URI",e);
  }
catch (  UnsupportedEncodingException e) {
    throw new IllegalArgumentException("Could not encode URI",e);
  }
}
