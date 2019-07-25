public static Collector<DeleteSeries,DeleteSeries> reduce(){
  return results -> {
    final List<RequestError> errors=new ArrayList<>();
    int deleted=0;
    int failed=0;
    for (    final DeleteSeries result : results) {
      errors.addAll(result.errors);
      deleted+=result.getDeleted();
      failed+=result.getFailed();
    }
    return new DeleteSeries(errors,deleted,failed);
  }
;
}
