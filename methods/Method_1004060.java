/** 
 * Subtracts the probes in the given  {@link ExecutionData} object from thestore. I.e. for all set probes in the given data object the corresponding probes in this store will be unset. If there is no execution data with id of the given data object this operation will have no effect.
 * @param data execution data to subtract
 * @throws IllegalStateException if the given  {@link ExecutionData} object is not compatibleto a corresponding one, that is already contained
 * @see ExecutionData#assertCompatibility(long,String,int)
 */
public void subtract(final ExecutionData data) throws IllegalStateException {
  final Long id=Long.valueOf(data.getId());
  final ExecutionData entry=entries.get(id);
  if (entry != null) {
    entry.merge(data,false);
  }
}
