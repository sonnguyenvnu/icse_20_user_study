/** 
 * Whether Flyway should skip the default callbacks. If true, only custom callbacks are used.
 * @param skipDefaultCallbacks Whether default built-in callbacks should be skipped. <p>(default: false)</p>
 */
public FluentConfiguration skipDefaultCallbacks(boolean skipDefaultCallbacks){
  config.setSkipDefaultCallbacks(skipDefaultCallbacks);
  return this;
}
