public synchronized <T>Entity<T> create(Class<T> classOfT,boolean dropIfExists){
  Entity<T> en=holder.getEntity(classOfT);
  if (exists(en.getTableName())) {
    if (dropIfExists) {
      expert.dropEntity(this,en);
    }
 else {
      expert.createRelation(this,en);
      return en;
    }
  }
  holder.remove(classOfT.getName());
  final Entity<T> _en=holder.getEntity(classOfT);
  expert.createEntity(this,_en);
  run(new ConnCallback(){
    public void invoke(    Connection conn) throws Exception {
      expert.setupEntityField(conn,_en);
    }
  }
);
  return en;
}
