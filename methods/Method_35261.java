@OnClick(R.id.btn_next_retain_view) void onNextWithRetainClicked(){
  setRetainViewMode(RetainViewMode.RETAIN_DETACH);
  getRouter().pushController(RouterTransaction.with(new TextController("Logcat should now report that the Controller's onDetach() and LifecycleObserver's onPause() methods were called.")).pushChangeHandler(new HorizontalChangeHandler()).popChangeHandler(new HorizontalChangeHandler()));
}
