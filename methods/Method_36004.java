private String urlPatternOrNull(Class<? extends UrlPattern> clazz,boolean regex){
  return (url != null && url.getClass().equals(clazz) && url.isRegex() == regex && url.isSpecified()) ? url.getPattern().getValue() : null;
}
