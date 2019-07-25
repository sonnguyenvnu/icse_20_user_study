public static boolean contains(ProvenanceEventType[] allowedEvents,ProvenanceEventType event){
  return Arrays.stream(allowedEvents).anyMatch(event::equals);
}
