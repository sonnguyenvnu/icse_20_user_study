protected Class<? extends ActionResult> parseActionResult(final Method actionMethod){
  final RenderWith renderWith=actionMethod.getAnnotation(RenderWith.class);
  if (renderWith != null) {
    return renderWith.value();
  }
  return null;
}
