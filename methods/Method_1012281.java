/** 
 * ????
 * @param width
 * @param maxHeight
 * @param onItemClickListener
 * @return
 */
public T create(int width,int maxHeight,final OnPopupItemClickListener onItemClickListener){
  create(width,maxHeight);
  setOnPopupItemClickListener(onItemClickListener);
  return (T)this;
}
