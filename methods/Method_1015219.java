public static <T,K,V>Collector<T,Map<K,V>,Map<K,V>> collector(Function<T,K> keyFn,Function<T,V> valFn){
  return collector(keyFn,valFn,Maps.MERGE_LAST_WRITE_WINS);
}
