/** 
 * Wrap some value in an observable value.
 * @param value Value to wrap.
 * @return Wrapped value.
 */
public static <T>ObservableValue<T> observable(T value){
  return new ReadOnlyObjectWrapper<>(value);
}
