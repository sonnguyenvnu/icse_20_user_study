/** 
 * Entry method for invoking customizable comparison, using passed-in {@link Comparator} object. Nodes will handle traversal of structuredtypes (arrays, objects), but defer to comparator for scalar value comparisons. If a "natural"  {@link Comparator} is passed -- one thatsimply calls <code>equals()</code> on one of arguments, passing the other -- implementation is the same as directly calling <code>equals()</code> on node. <p> Default implementation simply delegates to passed in <code>comparator</code>, with <code>this</code> as the first argument, and <code>other</code> as the second argument.
 * @param comparator Object called to compare two scalar {@link JsonNode} instances, and return either 0 (are equals) or non-zero (not equal)
 */
public boolean equals(Comparator<JsonNode> comparator,JsonNode other){
  return comparator.compare(this,other) == 0;
}
