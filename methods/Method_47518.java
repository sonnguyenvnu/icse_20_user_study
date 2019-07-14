protected void initToolbar(){
  if (SDK_INT >= LOLLIPOP) {
    getToolbar().setElevation(InterfaceUtils.dpToPixels(context,2));
    View view=findViewById(R.id.toolbarShadow);
    if (view != null)     view.setVisibility(GONE);
    view=findViewById(R.id.headerShadow);
    if (view != null)     view.setVisibility(GONE);
  }
}
