/** 
 * Apply  {@code actions} to {@code view}. 
 */
@UiThread @SafeVarargs public static <T extends View>void run(@NonNull T view,@NonNull Action<? super T>... actions){
  for (  Action<? super T> action : actions) {
    action.apply(view,0);
  }
}
