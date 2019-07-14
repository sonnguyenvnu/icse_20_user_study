/** 
 * Gets an unwritten character.
 * @param userId the specified user id
 * @return character
 */
public String getUnwrittenCharacter(final String userId){
  final String ret=getUnwrittenCharacterRandom(userId);
  if (StringUtils.isNotBlank(ret)) {
    return ret;
  }
  return getUnwrittenCharacterOneByOne(userId);
}
