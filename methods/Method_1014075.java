@Override public Thing remove(ThingUID thingUID){
  Thing thing=get(thingUID);
  if (thing != null) {
    notifyTrackers(thing,ThingTrackerEvent.THING_REMOVING);
  }
  return thing;
}
