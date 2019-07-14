@Override public void back(){
  if (this.bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
    hideBottomSheet();
  }
 else {
    super.back();
  }
}
