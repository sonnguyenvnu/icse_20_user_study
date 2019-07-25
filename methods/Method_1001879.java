public void closing(ImageWindow2 imageWindow){
  final boolean ok=openWindows2.remove(imageWindow);
  if (ok == false) {
    throw new IllegalStateException();
  }
}
