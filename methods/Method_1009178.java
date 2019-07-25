/** 
 * Highlights `textRange` with the given `textAttributesKey`.
 * @param textRange         textRange in the document to highlight
 * @param annotationHolder  the container which receives annotations created by the plugin.
 * @param textAttributesKey text attributes to apply to the `node`.
 */
private void highlight(@NotNull final TextRange textRange,@NotNull AnnotationHolder annotationHolder,@NotNull final TextAttributesKey textAttributesKey){
  annotationHolder.createInfoAnnotation(textRange,null).setEnforcedTextAttributes(TextAttributes.ERASE_MARKER);
  annotationHolder.createInfoAnnotation(textRange,null).setEnforcedTextAttributes(EditorColorsManager.getInstance().getGlobalScheme().getAttributes(textAttributesKey));
}
