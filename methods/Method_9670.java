private void updateBlurred(){
  if (isBlurred && blurredBitmap == null) {
    if (currentWallpaperBitmap != null) {
      blurredBitmap=Utilities.blurWallpaper(currentWallpaperBitmap);
    }
 else {
      ImageReceiver imageReceiver=backgroundImage.getImageReceiver();
      if (imageReceiver.hasNotThumb() || imageReceiver.hasStaticThumb()) {
        blurredBitmap=Utilities.blurWallpaper(imageReceiver.getBitmap());
      }
    }
  }
  if (isBlurred) {
    if (blurredBitmap != null) {
      backgroundImage.setImageBitmap(blurredBitmap);
    }
  }
 else {
    setCurrentImage(false);
  }
}
