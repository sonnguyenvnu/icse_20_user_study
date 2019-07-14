@Nullable public static GitHubErrorResponse getErrorResponse(@NonNull Throwable throwable){
  ResponseBody body=null;
  if (throwable instanceof HttpException) {
    body=((HttpException)throwable).response().errorBody();
  }
  if (body != null) {
    try {
      return gson.fromJson(body.string(),GitHubErrorResponse.class);
    }
 catch (    Exception ignored) {
    }
  }
  return null;
}
