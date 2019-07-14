@OnClick(R.id.btn_pick_title) void launchTitlePicker(){
  getRouter().pushController(RouterTransaction.with(new TargetTitleEntryController(this)).pushChangeHandler(new HorizontalChangeHandler()).popChangeHandler(new HorizontalChangeHandler()));
}
