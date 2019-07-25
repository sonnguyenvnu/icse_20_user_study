public static FileChannel wrap(FileChannel f){
  return new FileCache(f);
}
