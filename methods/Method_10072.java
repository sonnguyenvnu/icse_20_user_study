/** 
 * Updates the specified option by the given option id.
 * @param optionId the given option id
 * @param option   the specified option
 */
public void updateOption(final String optionId,final JSONObject option){
  final Transaction transaction=optionRepository.beginTransaction();
  try {
    optionRepository.update(optionId,option);
    transaction.commit();
  }
 catch (  final RepositoryException e) {
    if (transaction.isActive()) {
      transaction.rollback();
    }
    LOGGER.log(Level.ERROR,"Updates an option[id=" + optionId + "] failed",e);
  }
}
