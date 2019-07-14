/** 
 * Apply the specified  {@code action} across the {@code list} of views. 
 */
@UiThread public static <T extends View>void run(@NonNull List<T> list,@NonNull Action<? super T> action){
  for (int i=0, count=list.size(); i < count; i++) {
    action.apply(list.get(i),i);
  }
}
