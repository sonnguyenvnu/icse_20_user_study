public IocObject load(IocLoading loading,String name) throws ObjectLoadException {
  IocObject iobj=Iocs.wrap(this);
  iobj.setType(getClass());
  IocEventSet events=new IocEventSet();
  events.setDepose("depose");
  events.setCreate("init");
  events.setFetch("fetch");
  iobj.setEvents(events);
  return iobj;
}
