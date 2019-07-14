@OnClick(R.id.custom_tile_width_size) public void onWidthClick(){
  final NumberPicker view=new NumberPicker(this);
  view.setMinValue(24);
  view.setMaxValue(64);
  view.setWrapSelectorWheel(false);
  view.setValue(currentTileWidth);
  new AlertDialog.Builder(this).setView(view).setPositiveButton(android.R.string.ok,new DialogInterface.OnClickListener(){
    @Override public void onClick(    @NonNull DialogInterface dialog,    int which){
      currentTileWidth=view.getValue();
      widget.setTileWidthDp(currentTileWidth);
    }
  }
).show();
}
