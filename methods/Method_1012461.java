String dump(){
  if (data != null) {
    if (fileNum == 0)     fileNum=fileNumCounter++;
    AudioFileEntry ent=new AudioFileEntry();
    ent.fileName=fileName;
    ent.data=data;
    audioFileMap.put(fileNum,ent);
  }
  return super.dump() + " " + maxVoltage + " " + startPosition + " " + fileNum;
}
