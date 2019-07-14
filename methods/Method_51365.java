/** 
 * Specify the description of the property.
 * @param desc The description
 * @return The same builder
 */
@SuppressWarnings("unchecked") public T desc(String desc){
  if (StringUtils.isBlank(desc)) {
    throw new IllegalArgumentException("Description must be provided");
  }
  this.description=desc;
  return (T)this;
}
