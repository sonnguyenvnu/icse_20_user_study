public void addSuper(@NotNull Type superclass){
  this.superclass=superclass;
  table.setSuper(superclass.table);
  table.updateType("super",superclass);
}
