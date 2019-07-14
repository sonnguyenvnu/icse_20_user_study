/** 
 * Gets written character count of a user specified by the given user id.
 * @param userId the given user id
 * @return user written character count
 */
public int getWrittenCharacterCount(final String userId){
  final Query query=new Query().setFilter(new PropertyFilter(org.b3log.symphony.model.Character.CHARACTER_USER_ID,FilterOperator.EQUAL,userId));
  try {
    return (int)characterRepository.count(query);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Counts user written characters failed",e);
    return 0;
  }
}
