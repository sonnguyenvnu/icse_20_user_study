@OnClick(R.id.btn_next_retain_view) void onNextWithRetainClicked(){
  setRetainViewMode(RetainViewMode.RETAIN_DETACH);
  getRouter().pushController(RouterTransaction.with(new TextController("Logcat should now report that the observables from onAttach() has been disposed of, while the constructor and onViewBound() observables are still running.")).pushChangeHandler(new HorizontalChangeHandler()).popChangeHandler(new HorizontalChangeHandler()));
}
