/** 
 * Delete <T>, update valid flag to invalid.
 * @param inputParam input param
 */
@Transactional public void delete(Object inputParam) throws Exception {
  T po=findByIdParam(inputParam);
  repository.save(setInvalid(po));
}
