private long getPreparePositionWithOverride(long preparePositionUs){
  return preparePositionOverrideUs != C.TIME_UNSET ? preparePositionOverrideUs : preparePositionUs;
}
