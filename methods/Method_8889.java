private void updateSettingsButton(){
  int resource=R.drawable.photo_paint_brush;
  if (currentEntityView != null) {
    if (currentEntityView instanceof StickerView) {
      resource=R.drawable.photo_flip;
    }
 else     if (currentEntityView instanceof TextPaintView) {
      resource=R.drawable.photo_outline;
    }
    paintButton.setImageResource(R.drawable.photo_paint);
    paintButton.setColorFilter(null);
  }
 else {
    paintButton.setColorFilter(new PorterDuffColorFilter(0xff51bdf3,PorterDuff.Mode.MULTIPLY));
    paintButton.setImageResource(R.drawable.photo_paint);
  }
  colorPicker.setSettingsButtonImage(resource);
}
