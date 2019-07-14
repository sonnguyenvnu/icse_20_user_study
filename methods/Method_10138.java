/** 
 * Adds a verifycode with the specified request json object.
 * @param requestJSONObject the specified request json object, for example,{ "userId"; "", "type": int, "bizType": int, "receiver": "", "code": "", "status": int, "expired": long }
 * @return verifycode id
 * @throws ServiceException service exception
 */
@Transactional public String addVerifycode(final JSONObject requestJSONObject) throws ServiceException {
  try {
    return verifycodeRepository.add(requestJSONObject);
  }
 catch (  final RepositoryException e) {
    final String msg="Adds verifycode failed";
    LOGGER.log(Level.ERROR,msg,e);
    throw new ServiceException(msg);
  }
}
