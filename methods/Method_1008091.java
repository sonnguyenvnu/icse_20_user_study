@Override public ActionRequestValidationException validate(){
  ActionRequestValidationException validationException=super.validate();
  if (requestsPerSecond == null) {
    validationException=addValidationError("requests_per_second must be set",validationException);
  }
  for (  String action : getActions()) {
switch (action) {
case ReindexAction.NAME:
case UpdateByQueryAction.NAME:
case DeleteByQueryAction.NAME:
      continue;
default :
    validationException=addValidationError("Can only change the throttling on reindex or update-by-query. Not on [" + action + "]",validationException);
}
}
return validationException;
}
