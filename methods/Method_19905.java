private String[] extractAndDecodeHeader(String header,HttpServletRequest request){
  byte[] base64Token=header.substring(6).getBytes(StandardCharsets.UTF_8);
  byte[] decoded;
  try {
    decoded=Base64.getDecoder().decode(base64Token);
  }
 catch (  IllegalArgumentException var7) {
    throw new BadCredentialsException("Failed to decode basic authentication token");
  }
  String token=new String(decoded,StandardCharsets.UTF_8);
  int delim=token.indexOf(":");
  if (delim == -1) {
    throw new BadCredentialsException("Invalid basic authentication token");
  }
 else {
    return new String[]{token.substring(0,delim),token.substring(delim + 1)};
  }
}
