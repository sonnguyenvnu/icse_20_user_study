/** 
 * Adds the specified option.
 * @param option the specified option
 */
public void addOption(final JSONObject option){
  final Transaction transaction=optionRepository.beginTransaction();
  try {
    optionRepository.add(option);
    transaction.commit();
  }
 catch (  final RepositoryException e) {
    if (transaction.isActive()) {
      transaction.rollback();
    }
    LOGGER.log(Level.ERROR,"Adds an option failed",e);
  }
}
