/** 
 * ????? {@link EnumDict#getValue()}???.
 * @see this#find(Class, Predicate)
 */
static <T extends Enum & EnumDict<?>>Optional<T> findByValue(Class<T> type,Object value){
  return find(type,e -> e.getValue() == value || e.getValue().equals(value) || String.valueOf(e.getValue()).equalsIgnoreCase(String.valueOf(value)));
}
