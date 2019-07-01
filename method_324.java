synchronized void _XXXXX_(long lac,long len){
  if (lac > lastAddConfirmed) {
    lastAddConfirmed=lac;
    lacUpdateHitsCounter.inc();
  }
 else {
    lacUpdateMissesCounter.inc();
  }
  lastAddPushed=Math.max(lastAddPushed,lac);
  length=Math.max(length,len);
}