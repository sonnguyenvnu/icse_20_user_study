@Setup(Level.Trial) public void prepare() throws Exception {
  set=createSet(type,size);
  Random r=new Random(666);
  for (int i=0; i < occupancy - 1; i++) {
    set.add(new Key(r.nextInt(keyBound)));
  }
  key=new Key(r.nextInt(keyBound));
  set.add(key);
}
