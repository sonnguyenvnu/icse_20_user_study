/** 
 * Removes an option.
 * @param id the specified option id
 */
public void removeOption(final String id){
  final Transaction transaction=optionRepository.beginTransaction();
  try {
    optionRepository.remove(id);
    transaction.commit();
  }
 catch (  final RepositoryException e) {
    if (transaction.isActive()) {
      transaction.rollback();
    }
    LOGGER.log(Level.ERROR,"Removes an option failed",e);
  }
}
