/** 
 * Adds the specified operation.
 * @param operation the specified operation
 */
@Transactional public void addOperation(final JSONObject operation){
  try {
    operationRepository.add(operation);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Adds an operation failed",e);
  }
}
