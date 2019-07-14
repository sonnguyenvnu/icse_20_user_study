/** 
 * Returns whether the  {@link #initializationData}s belonging to this format and  {@code other} areequal.
 * @param other The other format whose {@link #initializationData} is being compared.
 * @return Whether the {@link #initializationData}s belonging to this format and  {@code other} areequal.
 */
public boolean initializationDataEquals(Format other){
  if (initializationData.size() != other.initializationData.size()) {
    return false;
  }
  for (int i=0; i < initializationData.size(); i++) {
    if (!Arrays.equals(initializationData.get(i),other.initializationData.get(i))) {
      return false;
    }
  }
  return true;
}
