Object route(Router pp,Session session,Route r2,Routeable hh,String[] keys,HttpRequest req,HttpResponse resp) throws Pausable, Exception {
  if (pp == null)   pp=supply(r2.source,null);
  if (hh instanceof Factory) {
    pp.init(session,req,resp);
    if (r2.prep != null)     r2.prep.accept(pp);
    Routeable h2=((Factory)hh).make(pp);
    return route(pp,session,r2,h2,keys,req,resp);
  }
  return route(hh,keys);
}
