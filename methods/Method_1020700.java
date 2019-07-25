/** 
 * Fall back to another Solo if the other Publisher signals or completes before this Solo signals a value.
 * @param other the other Publisher triggers the TimeoutException whenit signals its first item or completes.
 * @param fallback the fallback Solo to subscribe to in case of a timeout
 * @return the new Solo instance
 */
public final Solo<T> timeout(Publisher<?> other,Solo<T> fallback){
  ObjectHelper.requireNonNull(other,"other is null");
  ObjectHelper.requireNonNull(fallback,"fallback is null");
  return onAssembly(new SoloTimeout<T>(this,other,fallback));
}
