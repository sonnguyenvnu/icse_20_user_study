@OnClick(R.id.button_set_tile_size) void onTileSizeClicked(){
  final NumberPicker view=new NumberPicker(this);
  view.setMinValue(24);
  view.setMaxValue(64);
  view.setWrapSelectorWheel(false);
  view.setValue(currentTileSize);
  new AlertDialog.Builder(this).setView(view).setPositiveButton(android.R.string.ok,new DialogInterface.OnClickListener(){
    @Override public void onClick(    @NonNull DialogInterface dialog,    int which){
      currentTileSize=view.getValue();
      widget.setTileSizeDp(currentTileSize);
    }
  }
).show();
}
