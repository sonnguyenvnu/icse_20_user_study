/** 
 * Add this model to the given controller if the  {@link AddPredicate} return true. Can only becalled from inside  {@link EpoxyController#buildModels()}.
 */
public void addIf(@NonNull AddPredicate predicate,@NonNull EpoxyController controller){
  addIf(predicate.addIf(),controller);
}
