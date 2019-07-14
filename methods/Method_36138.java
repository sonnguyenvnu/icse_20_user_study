private boolean isAsynchronousResponseEnabled(){
  return optionSet.has(ASYNCHRONOUS_RESPONSE_ENABLED) ? Boolean.valueOf((String)optionSet.valueOf(ASYNCHRONOUS_RESPONSE_ENABLED)) : false;
}
