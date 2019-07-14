/** 
 * Set weights.
 * @param weights weights.
 */
public ZParams weights(final double... weights){
  params.add(WEIGHTS.raw);
  for (  final double weight : weights) {
    params.add(Protocol.toByteArray(weight));
  }
  return this;
}
