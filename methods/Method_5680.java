/** 
 * Sets the  {@link DynamicFormatFilter} to use when updating the selected track.
 * @param dynamicFormatFilter The {@link DynamicFormatFilter}.
 * @return This builder, for convenience.
 * @throws IllegalStateException If {@link #buildPlayerComponents()} has already been called.
 */
public BufferSizeAdaptationBuilder setDynamicFormatFilter(DynamicFormatFilter dynamicFormatFilter){
  Assertions.checkState(!buildCalled);
  this.dynamicFormatFilter=dynamicFormatFilter;
  return this;
}
