private boolean isItemEnabled(List<Entry<Integer,String>> list,int itemPosition){
  return list != null && itemPosition >= 0 && itemPosition < list.size() && list.get(itemPosition).getKey() == GridPickerAdapter.TYPE_CONTNET_ENABLE;
}
