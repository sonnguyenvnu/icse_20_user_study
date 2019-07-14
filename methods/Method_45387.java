private Resource getResource(final CookieContainer container){
  if (container.isForTemplate()) {
    return template(container.getTemplate());
  }
  return text(container.getValue());
}
