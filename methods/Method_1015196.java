/** 
 * @return a Java stream collector which can be used to construct a List
 */
public static <V>Collector<V,List<V>,List<V>> collector(){
  return new Collector<V,List<V>,List<V>>(){
    @Override public Supplier<List<V>> supplier(){
      return () -> new List().linear();
    }
    @Override public BiConsumer<List<V>,V> accumulator(){
      return List::addLast;
    }
    @Override public BinaryOperator<List<V>> combiner(){
      return (a,b) -> (List<V>)a.concat(b);
    }
    @Override public Function<List<V>,List<V>> finisher(){
      return List::forked;
    }
    @Override public java.util.Set<Characteristics> characteristics(){
      return EnumSet.noneOf(Characteristics.class);
    }
  }
;
}
