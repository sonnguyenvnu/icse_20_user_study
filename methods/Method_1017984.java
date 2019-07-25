/** 
 * Merges the specified value with this high water mark.
 */
public void accumulate(@Nullable final Long value){
  if (value != null && (this.value == null || this.value < value)) {
    if (client != null) {
      final String highWaterMarkValue=(formatter != null) ? formatter.apply(value) : value.toString();
      client.setHighWaterMarks(Collections.singletonMap(name,highWaterMarkValue));
    }
    this.value=value;
  }
}
