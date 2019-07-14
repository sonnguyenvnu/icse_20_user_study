/** 
 * Returns the actors to broadcast trace events to. 
 */
private List<Routee> makeRoutes(){
  return Registry.policies(settings).stream().map(policy -> {
    ActorRef actorRef=context().actorOf(Props.create(PolicyActor.class,policy));
    context().watch(actorRef);
    return new ActorRefRoutee(actorRef);
  }
).collect(toList());
}
