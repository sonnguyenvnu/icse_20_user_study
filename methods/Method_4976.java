static SpliceScheduleCommand parseFromSection(ParsableByteArray sectionData){
  int spliceCount=sectionData.readUnsignedByte();
  ArrayList<Event> events=new ArrayList<>(spliceCount);
  for (int i=0; i < spliceCount; i++) {
    events.add(Event.parseFromSection(sectionData));
  }
  return new SpliceScheduleCommand(events);
}
