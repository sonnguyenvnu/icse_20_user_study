public static ThreadFactory create(String namePrefix,boolean daemon){
  return new FastThreadLocalThreadFactory(namePrefix,daemon);
}
