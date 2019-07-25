/** 
 * Get the optioned value. Return default value if option does not exist.
 * @param option target option
 * @return the optioned value of default value if option does not exist.
 */
@SuppressWarnings("unchecked") public <T>T option(BoltOption<T> option){
  Object value=options.get(option);
  if (value == null) {
    value=option.defaultValue();
  }
  return value == null ? null : (T)value;
}
