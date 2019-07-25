public IEntry<Double,V> ceil(double key){
  return convertEntry(map.ceil(doubleToLong(key)));
}
