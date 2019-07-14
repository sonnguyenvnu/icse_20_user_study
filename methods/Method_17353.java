@Setup public void setup() throws IOException {
  settings=new BasicSettings(ConfigFactory.load().getConfig("caffeine.simulator"));
  events=readEventStream(settings).toArray();
}
