/** 
 * Apply  {@code action} to {@code view}. 
 */
@UiThread public static <T extends View>void run(@NonNull T view,@NonNull Action<? super T> action){
  action.apply(view,0);
}
