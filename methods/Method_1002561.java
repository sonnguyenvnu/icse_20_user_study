@Override protected Promise<? extends IndividualRequest> run(Context context) throws Throwable {
  if (_individualRequest.isFailed()) {
    return Promises.error(_individualRequest.getError());
  }
  IndividualRequest individualRequest=_individualRequest.get();
  if (_multiplexerSingletonFilter != null) {
    try {
      return Promises.value(_multiplexerSingletonFilter.filterIndividualRequest(individualRequest));
    }
 catch (    RestLiServiceException e) {
      return Promises.error(new IndividualResponseException(e,_errorResponseBuilder));
    }
catch (    Exception e) {
      return Promises.error(e);
    }
  }
  return Promises.value(individualRequest);
}
