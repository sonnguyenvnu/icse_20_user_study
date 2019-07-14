/** 
 * Main entry point.
 * @return the non-{@code null} but possibly empty list of additionalstyles for the doc string.
 */
public List<StyleRun> highlight(){
  if (resolveReferences) {
    scanCommentForTypeNames();
  }
  return styles;
}
