static public EntityGender and(final EntityGender g1,final EntityGender g2){
  return new EntityGender(){
    public boolean contains(    IEntity test){
      return g1.contains(test) && g2.contains(test);
    }
  }
;
}
