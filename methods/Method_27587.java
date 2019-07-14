private void setupPopupWindow(@NonNull ViewHolder viewHolder){
  if (popupWindow == null) {
    popupWindow=new PopupWindow(this);
    popupWindow.setElevation(getResources().getDimension(R.dimen.spacing_micro));
    popupWindow.setOutsideTouchable(true);
    popupWindow.setBackgroundDrawable(new ColorDrawable(ViewHelper.getWindowBackground(this)));
    popupWindow.setElevation(getResources().getDimension(R.dimen.spacing_normal));
    popupWindow.setOnDismissListener(() -> new Handler().postDelayed(() -> {
      if (assignee == null || milestone == null || sort == null || labels == null)       return;
      assignee.setTag(null);
      milestone.setTag(null);
      sort.setTag(null);
      labels.setTag(null);
    }
,100));
  }
  popupWindow.setContentView(viewHolder.view);
}
