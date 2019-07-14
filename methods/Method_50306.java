public static void showLoading(ViewGroup emptyView){
  if (emptyView == null) {
    return;
  }
  ProgressBar pbLoading=(ProgressBar)emptyView.getChildAt(0);
  pbLoading.setVisibility(View.VISIBLE);
  TextView tvEmptyMsg=(TextView)emptyView.getChildAt(1);
  tvEmptyMsg.setVisibility(View.GONE);
}
