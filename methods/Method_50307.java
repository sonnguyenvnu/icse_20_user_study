public static void showMessage(ViewGroup emptyView,String msg){
  if (emptyView == null) {
    return;
  }
  ProgressBar pbLoading=(ProgressBar)emptyView.getChildAt(0);
  pbLoading.setVisibility(View.GONE);
  TextView tvEmptyMsg=(TextView)emptyView.getChildAt(1);
  tvEmptyMsg.setVisibility(View.VISIBLE);
  tvEmptyMsg.setText(msg);
}
