private void hideBottomSheet(){
  this.bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
  this.bottomSheetScrim.setVisibility(View.GONE);
}
