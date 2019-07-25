/** 
 * Adds the given  {@link ExecutionData} object into the store. If there isalready execution data with this same class id, this structure is merged with the given one.
 * @param data execution data to add or merge
 * @throws IllegalStateException if the given  {@link ExecutionData} object is not compatibleto a corresponding one, that is already contained
 * @see ExecutionData#assertCompatibility(long,String,int)
 */
public void put(final ExecutionData data) throws IllegalStateException {
  final Long id=Long.valueOf(data.getId());
  final ExecutionData entry=entries.get(id);
  if (entry == null) {
    entries.put(id,data);
    names.add(data.getName());
  }
 else {
    entry.merge(data);
  }
}
