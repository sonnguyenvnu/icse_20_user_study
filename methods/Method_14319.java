static public HistoryEntry load(Project project,String s) throws IOException {
  ObjectMapper mapper=ParsingUtilities.mapper.copy();
  InjectableValues injection=new InjectableValues.Std().addValue("projectID",project.id);
  mapper.setInjectableValues(injection);
  return mapper.readValue(s,HistoryEntry.class);
}
