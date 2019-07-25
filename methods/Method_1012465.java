void play(){
  int i;
  JsArrayInteger arr=(JsArrayInteger)JsArrayInteger.createArray();
  int ct=dataPtr;
  int base=0;
  if (dataFull) {
    ct=dataCount;
    base=dataPtr;
  }
  if (ct * sampleStep < .05) {
    Window.alert(sim.LS("Audio data is not ready yet.  Increase simulation speed to make data ready sooner."));
    return;
  }
  double max=-1e8;
  double min=1e8;
  for (i=0; i != ct; i++) {
    if (data[i] > max)     max=data[i];
    if (data[i] < min)     min=data[i];
  }
  double adj=-(max + min) / 2;
  double mult=32766 / (max + adj);
  for (i=0; i != ct; i++) {
    int s=(int)((data[(i + base) % dataCount] + adj) * mult);
    arr.push(s);
  }
  playJS(arr,samplingRate);
}
