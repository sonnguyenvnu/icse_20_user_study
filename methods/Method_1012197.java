/** 
 * ??????????
 * @param id
 * @param enable
 * @return
 */
public RecyclerViewHolder enable(@IdRes int id,boolean enable){
  View view=findView(id);
  view.setEnabled(enable);
  if (view instanceof EditText) {
    view.setFocusable(enable);
    view.setFocusableInTouchMode(enable);
  }
  return this;
}
