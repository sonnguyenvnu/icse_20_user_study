public <T extends UriResponder>void setNotImplementedHandler(Class<T> handler){
  router.setNotImplemented(handler);
}
