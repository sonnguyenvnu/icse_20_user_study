public static ThreadFactory create(String namePrefix,boolean daemon){
  return new XpipeThreadFactory(getThreadName(namePrefix),daemon);
}
