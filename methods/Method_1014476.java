public void process(Stream<EasyLauncherFilter> filters){
  filters.forEach(filter -> filter.apply(image));
}
