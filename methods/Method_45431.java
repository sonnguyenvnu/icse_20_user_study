public ContentResource asTemplateResource(final String resourceName){
  ensureTemplate();
  if (hasProperties()) {
    return template(invokeTarget(resourceName,this.text,ContentResource.class),toVariables(this.props));
  }
  return template(invokeTarget(resourceName,this.text,ContentResource.class));
}
