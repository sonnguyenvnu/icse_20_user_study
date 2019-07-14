/** 
 * Returns a sequence of unique integers.
 * @param start the number that the counter starts from
 * @param events the number of events in the distribution
 */
public static LongStream counter(int start,int events){
  return generate(new CounterGenerator(start),events);
}
