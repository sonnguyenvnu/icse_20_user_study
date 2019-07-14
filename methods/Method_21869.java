@OnClick(R.id.button_simple_dialog) void onSimpleCalendarDialogClick(){
  new SimpleCalendarDialogFragment().show(getSupportFragmentManager(),"test-simple-calendar");
}
