public void initialize(){
  this.resourceOne.setDirectory(this.directory);
  this.resourceOne.setBeanFactory(this.beanFactory);
  this.resourceOne.setEndpoint(this.endpoint);
  this.resourceTwo.setDirectory(this.directory);
  this.resourceTwo.setBeanFactory(this.beanFactory);
  this.resourceTwo.setEndpoint(this.endpoint);
  byte masterFlagOne=this.resourceOne.initialize(true);
  byte masterFlagTwo=this.resourceTwo.initialize(false);
  if (masterFlagOne == 0x1 && masterFlagTwo == 0x0) {
    this.master=this.resourceOne;
    this.slaver=this.resourceTwo;
  }
 else   if (masterFlagOne == 0x0 && masterFlagTwo == 0x1) {
    this.master=this.resourceTwo;
    this.slaver=this.resourceOne;
  }
 else   if (masterFlagOne == 0x0 && masterFlagTwo == 0x0) {
    throw new IllegalStateException("Illegal state!");
  }
 else   if (masterFlagOne == 0x2 && masterFlagTwo == 0x1) {
    this.resourceTwo.markSlaver();
    this.resourceOne.markMaster();
    this.master=this.resourceOne;
    this.slaver=this.resourceTwo;
  }
 else   if (masterFlagOne == 0x2 && masterFlagTwo == 0x0) {
    this.resourceOne.markMaster();
    this.master=this.resourceOne;
    this.slaver=this.resourceTwo;
  }
 else   if (masterFlagOne == 0x1 && masterFlagTwo == 0x2) {
    this.resourceOne.markSlaver();
    this.resourceTwo.markMaster();
    this.master=this.resourceTwo;
    this.slaver=this.resourceOne;
  }
 else   if (masterFlagOne == 0x0 && masterFlagTwo == 0x2) {
    this.resourceTwo.markMaster();
    this.master=this.resourceTwo;
    this.slaver=this.resourceOne;
  }
 else {
    throw new IllegalStateException("Illegal state!");
  }
}
