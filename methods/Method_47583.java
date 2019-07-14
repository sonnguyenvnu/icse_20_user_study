@Override public void onDateChanged(){
  mAdapter.notifyDataSetChanged();
  postSetSelectionCentered(mController.getSelectedDay().year - mController.getMinYear());
}
