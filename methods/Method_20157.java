/** 
 * Notify that the given model has had its data changed. It should only be called if the model retained the same position.
 */
protected void notifyModelChanged(EpoxyModel<?> model){
  notifyModelChanged(model,null);
}
