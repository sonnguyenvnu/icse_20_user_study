@SuppressLint("InflateParams") @OnClick(R.id.milestone) public void onMilestoneClicked(){
  if (milestone.getTag() != null)   return;
  milestone.setTag(true);
  ViewHolder viewHolder=new ViewHolder(LayoutInflater.from(this).inflate(R.layout.simple_list_dialog,null));
  setupPopupWindow(viewHolder);
  viewHolder.recycler.setAdapter(getMilestonesAdapter());
  AnimHelper.revealPopupWindow(popupWindow,milestone);
}
