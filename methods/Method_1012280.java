/** 
 * ????
 * @param onItemClickListener
 * @return
 */
public T create(final OnPopupItemClickListener onItemClickListener){
  create(getPopupWidth());
  setOnPopupItemClickListener(onItemClickListener);
  return (T)this;
}
