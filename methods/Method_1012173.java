public void refresh(List<E> list){
  mList.clear();
  if (list != null && list.size() > 0) {
    mList.addAll(list);
  }
  notifyDataSetChanged();
}
