private void updateDrawable(@NonNull TabsCountStateModel model,TextView tv){
  model.setDrawableId(model.getDrawableId() == 0 ? R.drawable.ic_issue_opened_small : model.getDrawableId());
  tv.setCompoundDrawablePadding(16);
  tv.setCompoundDrawablesWithIntrinsicBounds(model.getDrawableId(),0,R.drawable.ic_arrow_drop_down,0);
}
