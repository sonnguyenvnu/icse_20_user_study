public void copy(LifxLightState other){
  this.powerState=other.getPowerState();
  this.colors=other.getColors();
  this.infrared=other.getInfrared();
  this.signalStrength=other.getSignalStrength();
}
