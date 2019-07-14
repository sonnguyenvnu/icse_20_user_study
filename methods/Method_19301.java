/** 
 * Returns the  {@link ComponentTree} for the item at index position. TODO 16212132 removegetComponentAt from binder
 */
@Nullable @Override public final synchronized ComponentTree getComponentAt(int position){
  return mComponentTreeHolders.get(position).getComponentTree();
}
