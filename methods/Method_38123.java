/** 
 * Sets batch parameters with given array of values.
 */
public Q setBatch(final String name,final long[] array,int startingIndex){
  init();
  final int batchSize=query.getBatchParameterSize(name);
  for (int i=1; i <= batchSize; i++) {
    final String paramName=name + '.' + i;
    if (startingIndex < array.length) {
      setLong(paramName,array[startingIndex]);
    }
 else {
      setNull(paramName,Types.INTEGER);
    }
    startingIndex++;
  }
  return _this();
}
