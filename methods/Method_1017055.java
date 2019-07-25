public static Collector<CountSeries,CountSeries> reduce(){
  return results -> {
    final List<RequestError> errors=new ArrayList<>();
    long count=0;
    boolean limited=false;
    for (    final CountSeries result : results) {
      errors.addAll(result.errors);
      count+=result.count;
      limited|=result.limited;
    }
    return new CountSeries(errors,count,limited);
  }
;
}
