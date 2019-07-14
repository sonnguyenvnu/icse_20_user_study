public ContentResource asResource(){
  if (isRawText()) {
    return text(this.text);
  }
  if (isForTemplate()) {
    return asTemplateResource();
  }
  return invokeTarget(getMethodName(),this.text,ContentResource.class);
}
