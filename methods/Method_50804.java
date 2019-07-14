Renderer createRenderer(){
  if (StringUtils.isBlank(type)) {
    throw new BuildException(unknownRendererMessage("<unspecified>"));
  }
  Properties properties=createProperties();
  Renderer renderer=RendererFactory.createRenderer(type,properties);
  renderer.setShowSuppressedViolations(showSuppressed);
  return renderer;
}
