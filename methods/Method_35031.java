@Override public SingleSource<BufferedSource> apply(Single<Parsed> upstream){
  return upstream.map(object -> adapter.toJson(object));
}
