/** 
 * @param data additional data
 */
public void addData(List<T> data){
  if (data != null) {
    int pos=getItemCount();
    this.mData.addAll(data);
    notifyItemRangeInserted(pos,data.size() - 1);
  }
}
