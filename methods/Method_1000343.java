public static Chain from(Object obj,FieldMatcher fm,Dao dao){
  final Chain[] chains=new Chain[1];
  boolean re=Daos.filterFields(obj,fm,dao,new Callback2<MappingField,Object>(){
    public void invoke(    MappingField mf,    Object val){
      if (mf.isReadonly() || !mf.isUpdate())       return;
      if (chains[0] == null)       chains[0]=Chain.make(mf.getName(),val);
 else       chains[0].add(mf.getName(),val);
    }
  }
);
  if (re)   return chains[0];
  return null;
}
