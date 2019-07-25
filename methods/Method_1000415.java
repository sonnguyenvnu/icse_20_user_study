@Override public Nesting select(String names,Class<?> clazz,Condition cnd){
  make(clazz);
  distinct(pojo,names);
  pojo.append(Pojos.Items.queryEntityFields());
  pojo.append(Pojos.Items.wrap("FROM"));
  pojo.append(Pojos.Items.entityViewName());
  pojo.append(Pojos.Items.cnd(cnd));
  if (cnd instanceof Cnd) {
    Pager pager=((Cnd)cnd).getPager();
    if (pager != null) {
      pojo.setPager(pager);
    }
  }
  expert.formatQuery(pojo);
  return this;
}
