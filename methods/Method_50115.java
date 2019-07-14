/** 
 * Returns a  {@link Value} representing a list of items. 
 */
private static Value listValue(Iterable<?> value){
  ListValue.Builder listValue=ListValue.newBuilder();
  for (  Object item : value) {
    listValue.addValues(value(item));
  }
  return Value.newBuilder().setListValue(listValue.build()).build();
}
