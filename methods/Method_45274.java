public static ContentResource jsonResource(final Resource resource){
  return contentResource(id("json"),jsonConfigApplier(resource),resource.reader(ContentResourceReader.class));
}
