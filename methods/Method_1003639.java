/** 
 * {@link #files(Action)}, using the default config.
 * @return {@code this}
 */
default Chain files(){
  return Exceptions.uncheck(() -> files(Action.noop()));
}
