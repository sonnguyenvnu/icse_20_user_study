/** 
 * Gets an unwritten character (strategy: Random).
 * @param userId the specified user id
 * @return character
 */
private String getUnwrittenCharacterRandom(final String userId){
  final String characters=langPropsService.get("characters");
  final int maxRetries=7;
  int retries=0;
  while (retries < maxRetries) {
    retries++;
    final int index=RandomUtils.nextInt(characters.length());
    final String ret=StringUtils.trim(characters.substring(index,index + 1));
    final Query query=new Query();
    query.setFilter(CompositeFilterOperator.and(new PropertyFilter(org.b3log.symphony.model.Character.CHARACTER_USER_ID,FilterOperator.EQUAL,userId),new PropertyFilter(org.b3log.symphony.model.Character.CHARACTER_CONTENT,FilterOperator.EQUAL,ret)));
    try {
      if (characterRepository.count(query) > 0) {
        continue;
      }
      return ret;
    }
 catch (    final RepositoryException e) {
      LOGGER.log(Level.ERROR,"Gets an unwritten character failed",e);
    }
  }
  return null;
}
