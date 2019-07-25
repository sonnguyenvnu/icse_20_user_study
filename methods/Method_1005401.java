public void update(float temp,float humidity,float pressure){
  tempSum+=temp;
  numReadings++;
  if (temp > maxTemp) {
    maxTemp=temp;
  }
  if (temp < minTemp) {
    minTemp=temp;
  }
  display();
}
