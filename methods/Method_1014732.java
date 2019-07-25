private static void extract(String root,Map<String,PropertySource<?>> map,PropertySource<?> source){
  if (source instanceof CompositePropertySource) {
    for (    PropertySource<?> nest : ((CompositePropertySource)source).getPropertySources()) {
      extract(source.getName() + ":",map,nest);
    }
  }
 else {
    map.put(root + source.getName(),source);
  }
}
