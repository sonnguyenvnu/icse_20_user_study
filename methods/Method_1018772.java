/** 
 * A weigher where the value is a  {@link Map} and its weight is the number ofentries. A map bounded with this weigher will evict when the total number of entries across all values exceeds the capacity rather than the number of key-value pairs in the map. <p> A value with a weight of <code>0</code> will be rejected by the map. If a value with this weight can occur then the caller should eagerly evaluate the value and treat it as a removal operation. Alternatively, a custom weigher may be specified on the map to assign an empty value a positive weight.
 * @param < A > A
 * @param < B > B
 * @return A weigher where each entry takes one unit of capacity.
 */
@SuppressWarnings({"cast","unchecked"}) public static <A,B>Weigher<? super Map<A,B>> map(){
  return (Weigher<Map<A,B>>)(Weigher<?>)MapWeigher.INSTANCE;
}
