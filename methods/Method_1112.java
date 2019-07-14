/** 
 * Sets callback to the drawable.
 * @param drawable drawable to set callbacks to
 * @param callback standard Android Drawable.Callback
 * @param transformCallback TransformCallback used by TransformAwareDrawables
 */
public static void setCallbacks(@Nullable Drawable drawable,@Nullable Drawable.Callback callback,@Nullable TransformCallback transformCallback){
  if (drawable != null) {
    drawable.setCallback(callback);
    if (drawable instanceof TransformAwareDrawable) {
      ((TransformAwareDrawable)drawable).setTransformCallback(transformCallback);
    }
  }
}
