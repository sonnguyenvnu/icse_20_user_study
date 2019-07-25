/** 
 * Persist this object in the data store.
 */
public void persist(){
  ++version;
  String curUserId=currentUserId();
  if (userId == null) {
    userId=curUserId;
  }
  if (curUserId.equals(userId)) {
    ofy().save().entity(this);
  }
}
