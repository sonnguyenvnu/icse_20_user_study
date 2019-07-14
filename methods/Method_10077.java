/** 
 * Checks whether the specified word is a reserved word.
 * @param word the specified word
 * @return {@code true} if it is a reserved word, returns {@code false} otherwise
 */
public boolean isReservedWord(final String word){
  final Query query=new Query().setFilter(CompositeFilterOperator.and(new PropertyFilter(Option.OPTION_VALUE,FilterOperator.EQUAL,word),new PropertyFilter(Option.OPTION_CATEGORY,FilterOperator.EQUAL,Option.CATEGORY_C_RESERVED_WORDS)));
  try {
    return optionRepository.count(query) > 0;
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Checks reserved word failed",e);
    return true;
  }
}
