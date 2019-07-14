private static @Nullable String getRedirectUrl(InvalidResponseCodeException exception){
  Map<String,List<String>> headerFields=exception.headerFields;
  if (headerFields != null) {
    List<String> locationHeaders=headerFields.get("Location");
    if (locationHeaders != null && !locationHeaders.isEmpty()) {
      return locationHeaders.get(0);
    }
  }
  return null;
}
