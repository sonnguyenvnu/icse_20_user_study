/** 
 * Removes a breezemoon with the specified id.
 * @param id the specified id
 * @throws ServiceException service exception
 */
@Transactional public void removeBreezemoon(final String id) throws ServiceException {
  try {
    breezemoonRepository.remove(id);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Removes a breezemoon [id=" + id + "] failed",e);
    throw new ServiceException(langPropsService.get("systemErrLabel"));
  }
}
