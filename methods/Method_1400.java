/** 
 * Sets the result to  {@code value}. <p> This method will return  {@code true} if the value was successfully set, or{@code false} if the data source has already been set, failed or closed.<p> If the value was successfully set and  {@code isLast} is {@code true}, state of the data source will be set to  {@link AbstractDataSource.DataSourceStatus#SUCCESS}. <p> This will also notify the subscribers if the value was successfully set.
 * @param value the value to be set
 * @param isLast whether or not the value is last.
 * @return true if the value was successfully set.
 */
@Override public boolean setResult(T value,boolean isLast){
  return super.setResult(Preconditions.checkNotNull(value),isLast);
}
