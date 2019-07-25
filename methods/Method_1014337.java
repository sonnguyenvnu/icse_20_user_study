private String link(CreateUserRequest request) throws IOException, ApiException {
  if (this.username != null) {
    throw new IllegalStateException("already linked");
  }
  String body=gson.toJson(request);
  Result result=http.post(getRelativeURL(""),body);
  handleErrors(result);
  List<SuccessResponse> entries=safeFromJson(result.getBody(),SuccessResponse.GSON_TYPE);
  SuccessResponse response=entries.get(0);
  return (String)response.success.get("username");
}
