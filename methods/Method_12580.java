@Nullable private String getResourcePath(String location,Resource resource) throws IOException {
  String locationWithouPrefix=location.replaceFirst("^[^:]+:","");
  Matcher m=Pattern.compile(Pattern.quote(locationWithouPrefix) + "(.+)$").matcher(resource.getURI().toString());
  if (m.find()) {
    return m.group(1);
  }
 else {
    return null;
  }
}
