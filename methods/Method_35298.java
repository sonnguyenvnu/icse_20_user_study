@OnClick(R.id.btn_use_title) void optionPicked(){
  Controller targetController=getTargetController();
  if (targetController != null) {
    ((TargetTitleEntryControllerListener)targetController).onTitlePicked(editText.getText().toString());
    getRouter().popController(this);
  }
}
