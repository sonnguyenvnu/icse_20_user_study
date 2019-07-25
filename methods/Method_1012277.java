/** 
 * ????
 * @param width               ?????
 * @param maxHeight           ???????
 * @param onItemClickListener ???????
 * @return
 */
public T create(int width,int maxHeight,AdapterView.OnItemClickListener onItemClickListener){
  create(width,maxHeight);
  mListView.setOnItemClickListener(onItemClickListener);
  return (T)this;
}
