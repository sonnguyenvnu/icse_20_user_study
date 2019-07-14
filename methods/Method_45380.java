@Override public ResponseHandler createResponseHandler(final ResponseSetting responseSetting){
  FluentIterable<ResponseHandler> handlers=from(getFields(responseSetting.getClass())).filter(isValidField(responseSetting)).transform(fieldToResponseHandler(responseSetting));
  return getResponseHandler(handlers);
}
