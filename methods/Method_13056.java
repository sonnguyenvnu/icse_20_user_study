public String loadSource(API api,Container.Entry entry){
  for (  SourceLoader provider : providers) {
    String source=provider.loadSource(api,entry);
    if ((source != null) && !source.isEmpty()) {
      return source;
    }
  }
  return null;
}
