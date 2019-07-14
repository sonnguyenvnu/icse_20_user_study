/** 
 * Updates a breezemoon with the specified request json object.
 * @param requestJSONObject the specified request json object, for example,"oId": "", "breezemoonContent": "", "breezemoonAuthorId": "", "breezemoonIP": "", "breezemoonUA": "", "breezemoonStatus": "" // optional, 0 as default
 * @throws ServiceException service exception
 */
@Transactional public void updateBreezemoon(final JSONObject requestJSONObject) throws ServiceException {
  final String content=requestJSONObject.optString(Breezemoon.BREEZEMOON_CONTENT);
  if (optionQueryService.containReservedWord(content)) {
    throw new ServiceException(langPropsService.get("contentContainReservedWordLabel"));
  }
  final String id=requestJSONObject.optString(Keys.OBJECT_ID);
  JSONObject old;
  try {
    old=breezemoonRepository.get(id);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Gets a breezemoon [id=" + id + "] failed",e);
    throw new ServiceException(langPropsService.get("systemErrLabel"));
  }
  if (null == old) {
    throw new ServiceException(langPropsService.get("queryFailedLabel"));
  }
  old.put(Breezemoon.BREEZEMOON_CONTENT,content);
  old.put(Breezemoon.BREEZEMOON_AUTHOR_ID,requestJSONObject.optString(Breezemoon.BREEZEMOON_AUTHOR_ID,old.optString(Breezemoon.BREEZEMOON_AUTHOR_ID)));
  old.put(Breezemoon.BREEZEMOON_IP,requestJSONObject.optString(Breezemoon.BREEZEMOON_IP));
  String ua=requestJSONObject.optString(Breezemoon.BREEZEMOON_UA);
  if (StringUtils.length(ua) > Common.MAX_LENGTH_UA) {
    ua=StringUtils.substring(ua,0,Common.MAX_LENGTH_UA);
  }
  old.put(Breezemoon.BREEZEMOON_UA,ua);
  old.put(Breezemoon.BREEZEMOON_STATUS,requestJSONObject.optInt(Breezemoon.BREEZEMOON_STATUS,Breezemoon.BREEZEMOON_STATUS_C_VALID));
  final long now=System.currentTimeMillis();
  old.put(Breezemoon.BREEZEMOON_UPDATED,now);
  try {
    breezemoonRepository.update(id,old);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Updates a breezemoon failed",e);
    throw new ServiceException(langPropsService.get("systemErrLabel"));
  }
}
