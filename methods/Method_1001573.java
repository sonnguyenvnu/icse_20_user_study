static public EntityGender all(){
  return new EntityGender(){
    public boolean contains(    IEntity test){
      return true;
    }
  }
;
}
