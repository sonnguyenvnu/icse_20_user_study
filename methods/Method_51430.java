/** 
 * Specify the description of the property. This is used for documentation. Please describe precisely how the property may change the behaviour of the rule. Providing complete information should be preferred over being concise. <p>Calling this method is required for  {@link #build()} to succeed.
 * @param desc The description
 * @return The same builder
 * @throws IllegalArgumentException If the description is null or whitespace
 */
@SuppressWarnings("unchecked") public B desc(String desc){
  if (StringUtils.isBlank(desc)) {
    throw new IllegalArgumentException("Description must be provided");
  }
  this.description=desc;
  return (B)this;
}
