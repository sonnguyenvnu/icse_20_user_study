/** 
 * ?????????
 * @param id
 * @param visibility
 * @return
 */
public RecyclerViewHolder visible(@IdRes int id,int visibility){
  View view=findView(id);
  view.setVisibility(visibility);
  return this;
}
