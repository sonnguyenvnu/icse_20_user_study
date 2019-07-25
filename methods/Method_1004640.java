private CompletableFuture<ResultData> combine(CompletableFuture<ResultData> future,CacheResult result){
  return future.thenCombine(result.future(),(d1,d2) -> {
    if (d1 == null) {
      return d2;
    }
    if (d1.getResultCode() != d2.getResultCode()) {
      return new ResultData(CacheResultCode.PART_SUCCESS,null,null);
    }
    return d1;
  }
);
}
