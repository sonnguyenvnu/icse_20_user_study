/** 
 * Adds highlight to perform as part of the search.
 */
public TopHitsAggregationBuilder highlighter(HighlightBuilder highlightBuilder){
  if (highlightBuilder == null) {
    throw new IllegalArgumentException("[highlightBuilder] must not be null: [" + name + "]");
  }
  this.highlightBuilder=highlightBuilder;
  return this;
}
