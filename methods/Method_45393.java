private Resource asResource(final String name,final Resource resource,final Optional<Charset> charset){
  if (charset.isPresent()) {
    return invokeTarget(name,resource,charset.get(),Resource.class,Resource.class,Charset.class);
  }
  return invokeTarget(name,resource,Resource.class,Resource.class);
}
