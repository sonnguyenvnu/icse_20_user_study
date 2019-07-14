/** 
 * ????
 * @param list
 */
@Override public synchronized void refresh(List<Entry<String,String>> list){
  if (list != null && list.size() > 0) {
    initList(list);
  }
  if (hasCheck) {
    selectedCount=0;
    for (int i=0; i < this.list.size(); i++) {
      if (getItemChecked(i) == true) {
        selectedCount++;
      }
    }
  }
  notifyDataSetChanged();
}
