/** 
 * Calculate a Holt-Linear (doubly exponential weighted) moving average
 * @param values Collection of values to calculate avg for
 * @param numForecasts number of forecasts into the future to return
 * @param < T >    Type T extending Number
 * @return       Returns a Double containing the moving avg for the window
 */
public <T extends Number>double[] next(Collection<T> values,int numForecasts){
  if (values.size() == 0) {
    return emptyPredictions(numForecasts);
  }
  double s=0;
  double last_s=0;
  double b=0;
  double last_b=0;
  int counter=0;
  T last;
  for (  T v : values) {
    last=v;
    if (counter == 1) {
      s=v.doubleValue();
      b=v.doubleValue() - last.doubleValue();
    }
 else {
      s=alpha * v.doubleValue() + (1.0d - alpha) * (last_s + last_b);
      b=beta * (s - last_s) + (1 - beta) * last_b;
    }
    counter+=1;
    last_s=s;
    last_b=b;
  }
  double[] forecastValues=new double[numForecasts];
  for (int i=0; i < numForecasts; i++) {
    forecastValues[i]=s + (i * b);
  }
  return forecastValues;
}
