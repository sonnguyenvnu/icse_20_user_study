@Override protected Referer<T> doSelect(final Request request){
  final Referer<T> referer=super.doSelect(request);
  final List<Referer<T>> refererList=getReferers();
  final HmilyTransactionContext hmilyTransactionContext=HmilyTransactionContextLocal.getInstance().get();
  if (Objects.isNull(hmilyTransactionContext)) {
    return referer;
  }
  final String transId=hmilyTransactionContext.getTransId();
  if (hmilyTransactionContext.getAction() == HmilyActionEnum.TRYING.getCode()) {
    URL_MAP.put(transId,referer.getUrl());
    return referer;
  }
  final URL orlUrl=URL_MAP.get(transId);
  URL_MAP.remove(transId);
  if (Objects.nonNull(orlUrl)) {
    for (    Referer<T> inv : refererList) {
      if (Objects.equals(inv.getUrl(),orlUrl)) {
        return inv;
      }
    }
  }
  return referer;
}
