/** 
 * Merge this capability with the specified argument. The service capabilities should match (i.e have the same  {@code id} and {@code type}). Sub-classes may merge additional content.
 * @param other the content to merge
 */
public void merge(ServiceCapability<T> other){
  Assert.notNull(other,"Other must not be null");
  Assert.isTrue(this.id.equals(other.id),"Ids must be equals");
  Assert.isTrue(this.type.equals(other.type),"Types must be equals");
  if (StringUtils.hasText(other.title)) {
    this.title=other.title;
  }
  if (StringUtils.hasText(other.description)) {
    this.description=other.description;
  }
  merge(other.getContent());
}
