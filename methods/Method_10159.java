/** 
 * Gets random avatar image byte array data with the specified hash.
 * @param hash the specified hash
 * @return avatar image byte array date, returns {@code null} if failed to get avatar
 */
public static byte[] getRandomAvatarData(final String hash){
  try {
    String h=hash;
    if (StringUtils.isBlank(h)) {
      h=RandomStringUtils.randomAlphanumeric(16);
    }
    final HttpResponse response=HttpRequest.get("http://www.gravatar.com/avatar/" + h + "?s=256&d=" + d[RandomUtils.nextInt(d.length)]).connectionTimeout(5000).timeout(5000).send();
    if (HttpServletResponse.SC_OK != response.statusCode()) {
      LOGGER.log(Level.WARN,"Gets avatar data failed [sc=" + response.statusCode() + "]");
      return null;
    }
    return response.bodyBytes();
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Gets avatar data failed",e);
    return null;
  }
}
