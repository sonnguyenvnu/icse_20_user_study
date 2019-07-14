/** 
 * Gets a breezemoon by the specified id.
 * @param breezemoonId the specified id
 * @return breezemoon, return {@code null} if not found
 */
public JSONObject getBreezemoon(final String breezemoonId){
  try {
    return breezemoonRepository.get(breezemoonId);
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Gets a breezemoon [id=" + breezemoonId + "] failed",e);
    return null;
  }
}
