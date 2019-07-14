/** 
 * @deprecated Use {@link ParametersBuilder#clearSelectionOverrides(int)}. 
 */
@Deprecated public final void clearSelectionOverrides(int rendererIndex){
  setParameters(buildUponParameters().clearSelectionOverrides(rendererIndex));
}
