/** 
 * Get an unmodifiable copy of the current models set on the adapter. 
 */
@NonNull public List<EpoxyModel<?>> getCopyOfModels(){
  return (List<EpoxyModel<?>>)getCurrentModels();
}
