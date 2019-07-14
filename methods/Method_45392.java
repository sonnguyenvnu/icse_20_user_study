private Resource fileResource(final String name,final FileContainer fileContainer){
  if (fileContainer.isForTemplate()) {
    if ("version".equalsIgnoreCase(name)) {
      return version(fileContainer.asTemplateResource());
    }
    return fileContainer.asTemplateResource(name);
  }
  TextContainer filename=fileContainer.getName();
  if (filename.isRawText()) {
    return asResource(name,fileContainer);
  }
  if (filename.isForTemplate()) {
    Optional<Charset> charset=fileContainer.getCharset();
    Resource resource=filename.asTemplateResource();
    return asResource(name,resource,charset);
  }
  throw new IllegalArgumentException(format("unknown file container:[%s]",fileContainer));
}
