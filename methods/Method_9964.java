/** 
 * Adds a breezemoon with the specified request json object.
 * @param requestJSONObject the specified request json object, for example,"breezemoonContent": "", "breezemoonAuthorId": "", "breezemoonIP": "", "breezemoonUA": "", "breezemoonCity": ""
 * @throws ServiceException service exception
 */
@Transactional public void addBreezemoon(final JSONObject requestJSONObject) throws ServiceException {
  final String content=requestJSONObject.optString(Breezemoon.BREEZEMOON_CONTENT);
  if (optionQueryService.containReservedWord(content)) {
    throw new ServiceException(langPropsService.get("contentContainReservedWordLabel"));
  }
  final JSONObject bm=new JSONObject();
  bm.put(Breezemoon.BREEZEMOON_CONTENT,content);
  bm.put(Breezemoon.BREEZEMOON_AUTHOR_ID,requestJSONObject.optString(Breezemoon.BREEZEMOON_AUTHOR_ID));
  bm.put(Breezemoon.BREEZEMOON_IP,requestJSONObject.optString(Breezemoon.BREEZEMOON_IP));
  String ua=requestJSONObject.optString(Breezemoon.BREEZEMOON_UA);
  if (StringUtils.length(ua) > Common.MAX_LENGTH_UA) {
    ua=StringUtils.substring(ua,0,Common.MAX_LENGTH_UA);
  }
  bm.put(Breezemoon.BREEZEMOON_UA,ua);
  final long now=System.currentTimeMillis();
  bm.put(Breezemoon.BREEZEMOON_CREATED,now);
  bm.put(Breezemoon.BREEZEMOON_UPDATED,now);
  bm.put(Breezemoon.BREEZEMOON_STATUS,Breezemoon.BREEZEMOON_STATUS_C_VALID);
  bm.put(Breezemoon.BREEZEMOON_CITY,requestJSONObject.optString(Breezemoon.BREEZEMOON_CITY));
  try {
    breezemoonRepository.add(bm);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Adds a breezemoon failed",e);
    throw new ServiceException(langPropsService.get("systemErrLabel"));
  }
}
