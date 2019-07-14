@SuppressLint("InflateParams") @OnClick(R.id.labels) public void onLabelsClicked(){
  if (labels.getTag() != null)   return;
  labels.setTag(true);
  ViewHolder viewHolder=new ViewHolder(LayoutInflater.from(this).inflate(R.layout.simple_list_dialog,null));
  setupPopupWindow(viewHolder);
  viewHolder.recycler.setAdapter(getLabelsAdapter());
  AnimHelper.revealPopupWindow(popupWindow,labels);
}
