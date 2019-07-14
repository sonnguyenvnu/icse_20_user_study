@Override public Resource resolve(String location,ResourceLoader resourceLoader){
  if (!location.startsWith(PROTOCOL)) {
    return null;
  }
  return new OssStorageResource(getOSS(),location);
}
