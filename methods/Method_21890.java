@OnClick(R.id.button_selection_mode) void onChangeSelectionMode(){
  new AlertDialog.Builder(this).setTitle("Selection Mode").setSingleChoiceItems(ITEMS,widget.getSelectionMode(),(dialog,which) -> {
    widget.setSelectionMode(which);
    dialog.dismiss();
  }
).show();
}
