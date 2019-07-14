private Resource resourceFrom(final String name,final TextContainer container){
  if (container.isFileContainer()) {
    return fileResource(name,FileContainer.class.cast(container));
  }
  return textResource(name,container);
}
