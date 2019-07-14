/** 
 * Add this model to the given controller. Can only be called from inside  {@link EpoxyController#buildModels()}.
 */
public void addTo(@NonNull EpoxyController controller){
  controller.addInternal(this);
}
