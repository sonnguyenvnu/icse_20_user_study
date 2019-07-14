/** 
 * create a custom ImageView instance
 * @param context activity context
 * @return an instance
 */
public static ImageView createImageInstance(Context context){
  if (sImageClass != null) {
    if (imageViewConstructor == null) {
      try {
        imageViewConstructor=sImageClass.getConstructor(Context.class);
      }
 catch (      NoSuchMethodException e) {
        e.printStackTrace();
      }
    }
    if (imageViewConstructor != null) {
      try {
        return (ImageView)imageViewConstructor.newInstance(context);
      }
 catch (      InstantiationException e) {
        e.printStackTrace();
      }
catch (      IllegalAccessException e) {
        e.printStackTrace();
      }
catch (      InvocationTargetException e) {
        e.printStackTrace();
      }
    }
  }
  return null;
}
