/** 
 * Use pattern matching to fill a column with the corresponding value (if it exists), otherwise null.
 * @param cols    the columns to fill in
 * @param allCols a set containing all columns of interest
 * @return a list containing the filled columns
 */
private static Column[] expr(final Set<String> cols,final Set<String> allCols){
  return allCols.stream().map(x -> {
    if (cols.contains(x)) {
      return col(x);
    }
 else {
      return lit(null).as(x);
    }
  }
).toArray(Column[]::new);
}
