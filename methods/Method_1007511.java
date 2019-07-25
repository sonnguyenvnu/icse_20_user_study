public <PP extends Router>PP scan(Scannable<PP> source,Preppable<PP> auth){
  ArrayList<Route> local=new ArrayList();
  PP pp=supply(source,new LocalConsumer(local));
  for (  Route rr : local)   addRoute(rr,new LocalScanner(pp),source,auth);
  return pp;
}
