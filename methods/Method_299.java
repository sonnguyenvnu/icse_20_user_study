/** 
 * Apply the specified  {@code actions} across the {@code list} of views. 
 */
@UiThread @SafeVarargs public static <T extends View>void run(@NonNull List<T> list,@NonNull Action<? super T>... actions){
  for (int i=0, count=list.size(); i < count; i++) {
    for (    Action<? super T> action : actions) {
      action.apply(list.get(i),i);
    }
  }
}
