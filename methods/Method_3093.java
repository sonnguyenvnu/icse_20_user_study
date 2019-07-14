public static void load(String path){
  IOUtil.LineIterator iterator=new IOUtil.LineIterator(path);
  iterator.next();
  while (iterator.hasNext()) {
    String[] args=iterator.next().split(",");
    if (args.length < 3)     continue;
    translator.put(args[1],args[2]);
  }
}
