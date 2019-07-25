public EntityObjectContext clone(){
  EntityObjectContext eoc=new EntityObjectContext(en,obj);
  if (!this.ext.isEmpty())   eoc.ext=new HashMap<String,Object>(this.ext);
  return eoc;
}
