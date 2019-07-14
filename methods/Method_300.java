/** 
 * Apply the specified  {@code actions} across the {@code array} of views. 
 */
@UiThread @SafeVarargs public static <T extends View>void run(@NonNull T[] array,@NonNull Action<? super T>... actions){
  for (int i=0, count=array.length; i < count; i++) {
    for (    Action<? super T> action : actions) {
      action.apply(array[i],i);
    }
  }
}
