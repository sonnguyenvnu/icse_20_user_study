/** 
 * @deprecated Use {@link ParametersBuilder#clearSelectionOverride(int,TrackGroupArray)}. 
 */
@Deprecated public final void clearSelectionOverride(int rendererIndex,TrackGroupArray groups){
  setParameters(buildUponParameters().clearSelectionOverride(rendererIndex,groups));
}
