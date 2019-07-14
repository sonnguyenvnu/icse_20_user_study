/** 
 * Returns a ticker that reads the current time using  {@link System#nanoTime}.
 * @return a ticker that reads the current time using {@link System#nanoTime}
 */
static @NonNull Ticker systemTicker(){
  return SystemTicker.INSTANCE;
}
