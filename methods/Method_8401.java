public float getCurrentProgress(){
  if (metaData[4] == 0) {
    return 0;
  }
  if (pendingSeekToUI >= 0) {
    return pendingSeekToUI / (float)metaData[4];
  }
  return metaData[3] / (float)metaData[4];
}
