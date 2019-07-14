public void storeToken(String resourceId,OAuthConsumerToken token){
  HttpSession session=getSession();
  session.setAttribute(KEY_PREFIX + "#" + resourceId,token);
  Long expiration=null;
  String expiresInValue=token.getAdditionalParameters() != null ? token.getAdditionalParameters().get("oauth_expires_in") : null;
  if (expiresInValue != null) {
    try {
      expiration=System.currentTimeMillis() + (Integer.parseInt(expiresInValue) * 1000);
    }
 catch (    NumberFormatException e) {
    }
  }
  if (expiration != null) {
    session.setAttribute(KEY_PREFIX + "#" + resourceId + "#EXPIRATION",expiration);
  }
}
