public ResponseHandler[] toResponseHandlers(){
  return from(collection).transform(toResponseHandler()).toArray(ResponseHandler.class);
}
