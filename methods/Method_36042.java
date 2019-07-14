@Override public boolean apply(ServeEvent serveEvent){
  if (!serveEvent.getResponseDefinition().isProxyResponse() && !allowNonProxied) {
    return false;
  }
  if (filters != null && !filters.match(serveEvent.getRequest()).isExactMatch()) {
    return false;
  }
  if (ids != null && !ids.contains(serveEvent.getId())) {
    return false;
  }
  return true;
}
