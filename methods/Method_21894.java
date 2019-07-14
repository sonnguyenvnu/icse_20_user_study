@OnClick(R.id.get_selection_mode) public void getSelectionModeClick(final View v){
  Toast.makeText(this,ITEMS[widget.getSelectionMode()],Toast.LENGTH_SHORT).show();
  Log.e("GettersActivity",ITEMS[widget.getSelectionMode()].toString());
}
