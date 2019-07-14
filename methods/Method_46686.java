@Override @Transactional public ExceptionList exceptionList(Integer page,Integer limit,Integer exState,String keyword,Integer registrar){
  if (Objects.isNull(page) || page <= 0) {
    page=1;
  }
  if (Objects.isNull(limit) || limit < 1) {
    limit=10;
  }
  Specification<TxException> specification=(Specification<TxException>)(root,cq,cb) -> {
    List<Predicate> predicatesList=new ArrayList<>();
    if ((Objects.nonNull(exState) && exState != -2) && (Objects.nonNull(registrar) && registrar != -2)) {
      predicatesList.add(cb.equal(root.get("exState"),exState));
      predicatesList.add(cb.equal(root.get("registrar"),registrar));
    }
 else     if (Objects.nonNull(exState) && exState != -2) {
      predicatesList.add(cb.equal(root.get("exState"),exState));
    }
 else     if (Objects.nonNull(registrar) && registrar != -2) {
      predicatesList.add(cb.equal(root.get("registrar"),registrar));
    }
    Predicate[] predicates=new Predicate[predicatesList.size()];
    return cb.and(predicatesList.toArray(predicates));
  }
;
  Page<TxException> pageTxExceptions=txExceptionRepository.findAll(specification,PageRequest.of(page - 1,limit));
  List<TxException> txExceptions=pageTxExceptions.getContent();
  List<ExceptionInfo> exceptionInfoList=new ArrayList<>(txExceptions.size());
  for (  TxException txException : txExceptions) {
    ExceptionInfo exceptionInfo=new ExceptionInfo();
    BeanUtils.copyProperties(txException,exceptionInfo);
    if (txException.getExState() != 1) {
      try {
        JSONObject transactionInfo=getTransactionInfo(exceptionInfo.getGroupId(),exceptionInfo.getUnitId());
        exceptionInfo.setTransactionInfo(transactionInfo);
      }
 catch (      TransactionStateException e) {
        if (e.getCode() == TransactionStateException.NON_ASPECT) {
          txExceptionRepository.changeExState(txException.getId(),(short)1);
          exceptionInfo.setExState((short)1);
        }
      }
    }
    exceptionInfoList.add(exceptionInfo);
  }
  ExceptionList exceptionList=new ExceptionList();
  exceptionList.setTotal(pageTxExceptions.getTotalElements());
  exceptionList.setExceptions(exceptionInfoList);
  return exceptionList;
}
