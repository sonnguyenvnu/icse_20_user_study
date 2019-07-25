/** 
 * Simply returns its argument.
 * @deprecated no need to use this
 * @since NEXT
 */
@Deprecated public static <V>FluentFuture<V> from(FluentFuture<V> future){
  return checkNotNull(future);
}
