/** 
 * Apply the specified  {@code action} across the {@code array} of views. 
 */
@UiThread public static <T extends View>void run(@NonNull T[] array,@NonNull Action<? super T> action){
  for (int i=0, count=array.length; i < count; i++) {
    action.apply(array[i],i);
  }
}
