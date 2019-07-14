@OnClick(R.id.btn_next) void onNextClicked(){
  getRouter().pushController(RouterTransaction.with(new NavigationDemoController(index + 1,displayUpMode.getDisplayUpModeForChild())).pushChangeHandler(new HorizontalChangeHandler()).popChangeHandler(new HorizontalChangeHandler()));
}
