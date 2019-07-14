public void generateZip(List<CompressedObjectParcelable> arrayList){
  offset=0;
  stoppedAnimation=false;
  items=arrayList;
  notifyDataSetChanged();
  itemsChecked=new boolean[items.size()];
}
