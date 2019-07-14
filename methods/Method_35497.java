/** 
 * Returns a string representing the JSONPointer path value using URI fragment identifier representation
 */
public String toURIFragment(){
  try {
    StringBuilder rval=new StringBuilder("#");
    for (    String token : this.refTokens) {
      rval.append('/').append(URLEncoder.encode(token,ENCODING));
    }
    return rval.toString();
  }
 catch (  UnsupportedEncodingException e) {
    throw new RuntimeException(e);
  }
}
