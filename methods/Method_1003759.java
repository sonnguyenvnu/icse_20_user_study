/** 
 * Creates an bi-action from a JDK bi-consumer.
 * @param consumer the JDK consumer
 * @param < T > the type of the first object this action accepts
 * @param < U > the type of the second object this action accepts
 * @return the given consumer as an action
 */
static <T,U>BiAction<T,U> from(BiConsumer<T,U> consumer){
  return consumer::accept;
}
