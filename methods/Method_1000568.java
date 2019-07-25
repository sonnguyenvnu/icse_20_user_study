public Object born(IocMaking ing){
  Object[] args=new Object[this.args.length];
  for (int i=0; i < args.length; i++)   args[i]=this.args[i].get(ing);
  Object obj=borning.born(args);
  if (shallTrigger(obj)) {
    for (    IocEventListener listener : listeners) {
      obj=listener.afterBorn(obj,beanName);
    }
  }
  return obj;
}
