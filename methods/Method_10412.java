public static Header[] deserializeHeaders(String[] serialized){
  if (serialized == null || serialized.length % 2 != 0) {
    return new Header[0];
  }
  Header[] headers=new Header[serialized.length / 2];
  for (int i=0, h=0; h < headers.length; i++, h++) {
    headers[h]=new BasicHeader(serialized[i],serialized[++i]);
  }
  return headers;
}
