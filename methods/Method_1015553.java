public void start() throws Exception {
  Map<String,Object> map=new HashMap<>();
  map.put("state_transfer",true);
  map.put("protocol_class",getClass().getName());
  up_prot.up(new Event(Event.CONFIG,map));
  if (buffer_size <= 0)   throw new IllegalArgumentException("buffer_size has to be > 0");
}
