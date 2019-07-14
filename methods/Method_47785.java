private void init(){
  ring=(RingView)findViewById(R.id.scoreRing);
  label=(TextView)findViewById(R.id.label);
  if (ring != null)   ring.setIsTransparencyEnabled(true);
  if (isInEditMode()) {
    percentage=0.75f;
    name="Wake up early";
    activeColor=PaletteUtils.getAndroidTestColor(6);
    checkmarkValue=Checkmark.CHECKED_EXPLICITLY;
    refresh();
  }
}
