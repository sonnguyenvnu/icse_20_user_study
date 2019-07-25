public @Nullable BlukiiData decode(final byte[] data){
  if (data.length < 22) {
    logger.debug("Blukii data length to short (skip decoding): {}",HexUtils.bytesToHex(data," "));
    return null;
  }
  if (data[0] != 0x4F) {
    return null;
  }
  if (logger.isDebugEnabled()) {
    logger.debug("Decode Blukii data: {}",HexUtils.bytesToHex(data," "));
  }
  final int battery=data[12] & 0x7F;
  final Optional<Magnetometer> magnetometer;
  final Optional<Environment> environment;
  final Optional<Accelerometer> accelerometer;
  if ((data[14] & 0x30) == 0x30) {
    magnetometer=Optional.of(processMagnetometerData(data));
    environment=Optional.empty();
    accelerometer=Optional.empty();
  }
 else   if ((data[14] & 0x10) == 0x10) {
    magnetometer=Optional.empty();
    environment=Optional.of(processEnvironmentData(data));
    accelerometer=Optional.empty();
  }
 else   if ((data[14] & 0x20) == 0x20) {
    magnetometer=Optional.empty();
    environment=Optional.empty();
    accelerometer=Optional.of(processAccelerometerData(data));
  }
 else {
    magnetometer=Optional.empty();
    environment=Optional.empty();
    accelerometer=Optional.empty();
  }
  return new BlukiiData(battery,magnetometer,environment,accelerometer);
}
