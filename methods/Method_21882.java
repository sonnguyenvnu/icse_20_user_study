@OnClick(R.id.button_set_width_height) void onTileWidthHeightClicked(){
  final LinearLayout layout=new LinearLayout(this);
  layout.setOrientation(LinearLayout.HORIZONTAL);
  final NumberPicker pickerWidth=new NumberPicker(this);
  pickerWidth.setMinValue(24);
  pickerWidth.setMaxValue(64);
  pickerWidth.setWrapSelectorWheel(false);
  pickerWidth.setValue(currentTileWidth);
  final NumberPicker pickerHeight=new NumberPicker(this);
  pickerHeight.setMinValue(24);
  pickerHeight.setMaxValue(64);
  pickerHeight.setWrapSelectorWheel(false);
  pickerHeight.setValue(currentTileHeight);
  layout.addView(pickerWidth);
  layout.addView(pickerHeight);
  new AlertDialog.Builder(this).setView(layout).setPositiveButton(android.R.string.ok,new DialogInterface.OnClickListener(){
    @Override public void onClick(    @NonNull DialogInterface dialog,    int which){
      currentTileWidth=pickerWidth.getValue();
      currentTileHeight=pickerHeight.getValue();
      widget.setTileSize(-1);
      widget.setTileWidthDp(currentTileWidth);
      widget.setTileHeightDp(currentTileHeight);
    }
  }
).show();
}
