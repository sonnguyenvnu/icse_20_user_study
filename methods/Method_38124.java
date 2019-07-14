/** 
 * Sets batch parameters with given array of values.
 */
public Q setBatch(final String name,final Object[] array,int startingIndex){
  init();
  final int batchSize=query.getBatchParameterSize(name);
  for (int i=1; i <= batchSize; i++) {
    final String paramName=name + '.' + i;
    if (startingIndex < array.length) {
      setObject(paramName,array[startingIndex]);
    }
 else {
      setObject(paramName,null);
    }
    startingIndex++;
  }
  return _this();
}
