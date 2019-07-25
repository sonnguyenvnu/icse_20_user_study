public void start() throws Exception {
  Map<String,Object> map=new HashMap<>();
  map.put("state_transfer",Boolean.TRUE);
  map.put("protocol_class",getClass().getName());
  up_prot.up(new Event(Event.CONFIG,map));
}
