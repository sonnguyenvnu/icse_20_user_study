/** 
 * Turned into a separate method so that anyone needed update.id will get a legit answer. Had a problem with the contribs script where the id wouldn't be set so a null id would be sent to the contribs server.
 */
static public long getUpdateID(){
  Random r=new Random();
  long id=r.nextLong();
  String idString=Preferences.get("update.id");
  if (idString != null) {
    id=Long.parseLong(idString);
  }
 else {
    Preferences.set("update.id",String.valueOf(id));
  }
  return id;
}
