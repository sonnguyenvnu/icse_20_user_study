@OnClick(R.id.button_selection_mode) void onChangeSelectionMode(){
  CharSequence[] items={"No Selection","Single Date","Multiple Dates","Range of Dates"};
  new AlertDialog.Builder(this).setTitle("Selection Mode").setSingleChoiceItems(items,widget.getSelectionMode(),new DialogInterface.OnClickListener(){
    @Override public void onClick(    DialogInterface dialog,    int which){
      widget.setSelectionMode(which);
      dialog.dismiss();
    }
  }
).show();
}
