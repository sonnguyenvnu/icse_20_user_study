@Override public ErrorType judge(OAuth2Response response){
  if (response.status() == 200) {
    return null;
  }
  String result=response.asString();
  if (result == null) {
    return ErrorType.OTHER;
  }
  return errorTypes.stream().filter(errorType -> result.contains(errorType.name().toLowerCase())).findAny().orElse(null);
}
