/** 
 * Returns a singleton  {@link ThrottlingStrategy} that always accepts requests.
 */
@SuppressWarnings("unchecked") public static <T extends Request>ThrottlingStrategy<T> always(){
  return (ThrottlingStrategy<T>)ALWAYS;
}
