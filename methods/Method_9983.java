/** 
 * Gets an unwritten character (strategy: One By One).
 * @return character
 */
private String getUnwrittenCharacterOneByOne(){
  final String characters=langPropsService.get("characters");
  int index=0;
  while (true) {
    if (index > characters.length()) {
      return null;
    }
    final String ret=StringUtils.trim(characters.substring(index,index + 1));
    index++;
    final Query query=new Query().setFilter(new PropertyFilter(org.b3log.symphony.model.Character.CHARACTER_CONTENT,FilterOperator.EQUAL,ret));
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
}
