@Override public void run(String... strings) throws Exception {
  kafkaTemplate.send("kafkaTest","hello");
}
