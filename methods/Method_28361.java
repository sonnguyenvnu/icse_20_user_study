private void hideFilterLayout(){
  AnimHelper.mimicFabVisibility(false,filterLayout,new FloatingActionButton.OnVisibilityChangedListener(){
    @Override public void onHidden(    FloatingActionButton actionButton){
      fab.show();
    }
  }
);
}
