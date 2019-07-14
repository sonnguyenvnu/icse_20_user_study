/** 
 * Is this update about a new item?
 */
public boolean isNew(){
  return EntityIdValue.SITE_LOCAL.equals(getItemId().getSiteIri());
}
