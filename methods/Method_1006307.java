@Setup public void init(){
  long target_cardinality=Math.round(density * size);
  long actual_cardinality=0;
  ThreadLocalRandom random=ThreadLocalRandom.current();
  while (actual_cardinality < target_cardinality) {
    int x=random.nextInt(size);
    actual_cardinality+=bitmap.checkedAdd(x) ? 1 : 0;
    bs.set(x);
  }
  for (int k=0; k < random_size; k++) {
    random_array[k]=random.nextInt(size);
  }
}
