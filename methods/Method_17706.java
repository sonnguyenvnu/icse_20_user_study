@Nullable private static Object resolveReference(WeakReference<Object> mountContentRef){
  final Object mountContent=mountContentRef != null ? mountContentRef.get() : null;
  if (mountContent == null) {
    return null;
  }
  if ((mountContent instanceof Drawable) && ((Drawable)mountContent).getCallback() == null) {
    mountContentRef.clear();
    return null;
  }
  return mountContent;
}
