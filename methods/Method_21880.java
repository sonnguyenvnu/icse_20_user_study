@OnClick(R.id.button_set_colors) void onColorsClicked(){
  int color=Color.HSVToColor(new float[]{random.nextFloat() * 360,1f,0.75f});
  widget.setSelectionColor(color);
}
