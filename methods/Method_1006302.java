@Setup(Level.Trial) public void init(){
  this.buffer=new int[bufferSize];
  this.bitmap=RandomData.randomBitmap(keys,runniness,dirtiness);
}
