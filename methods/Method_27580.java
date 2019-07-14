@SuppressLint("InflateParams") @OnClick(R.id.assignee) public void onAssigneeClicked(){
  if (assignee.getTag() != null)   return;
  assignee.setTag(true);
  ViewHolder viewHolder=new ViewHolder(LayoutInflater.from(this).inflate(R.layout.simple_list_dialog,null));
  setupPopupWindow(viewHolder);
  viewHolder.recycler.setAdapter(getAssigneesAdapter());
  AnimHelper.revealPopupWindow(popupWindow,assignee);
}
