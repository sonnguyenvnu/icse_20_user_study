public List<T> shuffle(List<T> list,long time){
  long headBlockTimeHi=time << 32;
  for (int i=0; i < list.size(); i++) {
    long v=headBlockTimeHi + i * RANDOM_GENERATOR_NUMBER;
    v=v ^ (v >> 12);
    v=v ^ (v << 25);
    v=v ^ (v >> 27);
    v=v * RANDOM_GENERATOR_NUMBER;
    int index=(int)(i + v % (list.size() - i));
    if (index < 0 || index >= list.size()) {
      logger.warn("index[" + index + "] is out of range[0," + (list.size() - 1) + "],skip");
      continue;
    }
    T tmp=list.get(index);
    list.set(index,list.get(i));
    list.set(i,tmp);
  }
  return list;
}
