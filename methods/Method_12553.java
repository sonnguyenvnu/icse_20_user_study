private boolean requiresBody(HttpMethod method){
switch (method) {
case PUT:
case POST:
case PATCH:
    return true;
default :
  return false;
}
}
