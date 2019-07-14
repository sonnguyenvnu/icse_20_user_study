@Nullable private static View getHostView(Drawable drawable){
  Drawable.Callback callback;
  while (true) {
    callback=drawable.getCallback();
    if (callback instanceof Drawable) {
      drawable=(Drawable)callback;
    }
 else     if (callback instanceof View) {
      return (View)callback;
    }
 else {
      return null;
    }
  }
}
