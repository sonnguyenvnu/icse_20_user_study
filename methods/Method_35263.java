@OnClick(R.id.btn_next_release_view) void onNextWithReleaseClicked(){
  setRetainViewMode(RetainViewMode.RELEASE_DETACH);
  getRouter().pushController(RouterTransaction.with(new TextController("Logcat should now report that the observables from onAttach() and onViewBound() have been disposed of, while the constructor observable is still running.")).pushChangeHandler(new HorizontalChangeHandler()).popChangeHandler(new HorizontalChangeHandler()));
}
