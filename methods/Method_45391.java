private Resource textResource(final String name,final TextContainer container){
  if (container.isRawText()) {
    return invokeTarget(name,container.getText(),Resource.class);
  }
  if (container.isForTemplate()) {
    if ("version".equalsIgnoreCase(name)) {
      return version(container.asTemplateResource());
    }
    return container.asTemplateResource(name);
  }
  throw new IllegalArgumentException(format("unknown text container:[%s]",container));
}
