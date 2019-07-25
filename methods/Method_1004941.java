public void put(final byte serialiserEncoding,final ToBytesSerialiser serialiser,final Class supportedClass) throws GafferCheckedException {
  validatePutParams(serialiser,supportedClass);
  consistent=continuesToBeConsistant(serialiser);
  preservesObjectOrdering=continuesToPreserveOrdering(serialiser);
  keyToSerialiser.put(serialiserEncoding,serialiser);
  keyToClass.put(serialiserEncoding,supportedClass);
  classToKey.put(supportedClass,serialiserEncoding);
}
