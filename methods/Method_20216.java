/** 
 * Sets fields of the model to default ones.
 */
@NonNull public EpoxyModel<T> reset(){
  onMutation();
  layout=0;
  shown=true;
  return this;
}
