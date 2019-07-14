/** 
 * The previous list was empty and the given non empty list was inserted. 
 */
static DiffResult inserted(@NonNull List<? extends EpoxyModel<?>> newModels){
  return new DiffResult(Collections.EMPTY_LIST,newModels,null);
}
