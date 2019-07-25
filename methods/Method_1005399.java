public void update(float temp,float humidity,float pressure){
  lastPressure=currentPressure;
  currentPressure=pressure;
  display();
}
