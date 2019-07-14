void storeNamedParameter(final String name,final int position){
  DbQueryNamedParameter p=lookupNamedParameter(name);
  if (p == null) {
    p=new DbQueryNamedParameter(name);
    p.indices=new int[]{position};
    p.next=rootNP;
    rootNP=p;
  }
 else {
    p.add(position);
  }
}
