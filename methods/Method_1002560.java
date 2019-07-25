@Override protected Promise<? extends IndividualRequest> run(Context context) throws Throwable {
  if (_individualRequest.isFailed()) {
    return Promises.error(_individualRequest.getError());
  }
  IndividualRequest individualRequest=_individualRequest.get();
  inheritHeaders(individualRequest,_envelopeRequest);
  return Promises.value(individualRequest);
}
