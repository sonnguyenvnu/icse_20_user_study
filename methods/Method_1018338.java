private static String prefix(HttpMethod method){
switch (method) {
case GET:
    return "get-";
case POST:
  return "create-";
case DELETE:
return "delete-";
case PUT:
return "update-";
case PATCH:
return "patch-";
default :
throw new IllegalArgumentException(method.name());
}
}
