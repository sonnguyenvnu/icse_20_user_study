/** 
 * Show logs.
 * @return {@link Page}
 * @throws CommonsException {@link com.saintdan.framework.enums.ErrorType#SYS0121} No groupexists.
 */
@SuppressWarnings("unchecked") public Page page(Specification<Log> specification,Pageable pageable) throws Exception {
  Page<Log> logs=logRepository.findAll(specification,pageable);
  if (!logs.hasContent()) {
    return new PageImpl<>(new ArrayList<>(),pageable,logs.getTotalElements());
  }
  return logs;
}
