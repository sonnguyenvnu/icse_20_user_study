@OnClick(R.id.reload) void onReload(){
  if (onReloadListener != null) {
    onReloadListener.onClick(reload);
  }
}
