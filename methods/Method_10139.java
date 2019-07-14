/** 
 * Removes expired verifycodes.
 */
@Transactional public void removeExpiredVerifycodes(){
  final Query query=new Query().setFilter(new PropertyFilter(Verifycode.EXPIRED,FilterOperator.LESS_THAN,new Date().getTime()));
  try {
    final JSONObject result=verifycodeRepository.get(query);
    final JSONArray verifycodes=result.optJSONArray(Keys.RESULTS);
    for (int i=0; i < verifycodes.length(); i++) {
      final String id=verifycodes.optJSONObject(i).optString(Keys.OBJECT_ID);
      verifycodeRepository.remove(id);
    }
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Expires verifycodes failed",e);
  }
}
