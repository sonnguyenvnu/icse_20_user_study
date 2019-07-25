public static <V>Collector<V,Set<V>,Set<V>> collector(){
  return new Collector<V,Set<V>,Set<V>>(){
    @Override public Supplier<Set<V>> supplier(){
      return () -> new Set<V>().linear();
    }
    @Override public BiConsumer<Set<V>,V> accumulator(){
      return Set::add;
    }
    @Override public BinaryOperator<Set<V>> combiner(){
      return Set::union;
    }
    @Override public Function<Set<V>,Set<V>> finisher(){
      return Set::forked;
    }
    @Override public java.util.Set<Characteristics> characteristics(){
      return EnumSet.of(Characteristics.UNORDERED);
    }
  }
;
}
