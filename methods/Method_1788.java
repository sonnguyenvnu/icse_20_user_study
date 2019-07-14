private int getValueSizeInBytes(V value){
  return (value == null) ? 0 : mValueDescriptor.getSizeInBytes(value);
}
