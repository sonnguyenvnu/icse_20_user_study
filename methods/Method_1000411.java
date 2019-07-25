public <T>List<T> query(final Class<T> classOfT,final Condition cnd,final Pager pager,String regex){
  if (regex == null)   return query(classOfT,cnd,pager);
  FieldFilter ff=FieldFilter.create(classOfT,FieldMatcher.make(regex,null,false));
  Molecule<List<T>> m=new Molecule<List<T>>(){
    public void run(){
      setObj(query(classOfT,cnd,pager));
    }
  }
;
  return ff.run(m);
}
