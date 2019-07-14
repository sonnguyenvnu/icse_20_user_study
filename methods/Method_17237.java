/** 
 * Returns a ticker that always returns  {@code 0}.
 * @return a ticker that always returns {@code 0}
 */
static @NonNull Ticker disabledTicker(){
  return DisabledTicker.INSTANCE;
}
