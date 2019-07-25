/** 
 * Calculate a doubly exponential weighted moving average
 * @param values Collection of values to calculate avg for
 * @param numForecasts number of forecasts into the future to return
 * @param < T >    Type T extending Number
 * @return       Returns a Double containing the moving avg for the window
 */
public <T extends Number>double[] next(Collection<T> values,int numForecasts){
  if (values.size() < period * 2) {
    throw new AggregationExecutionException("Holt-Winters aggregation requires at least (2 * period == 2 * " + period + " == " + (2 * period) + ") data-points to function.  Only [" + values.size() + "] were provided.");
  }
  double s=0;
  double last_s;
  double b=0;
  double last_b=0;
  double[] seasonal=new double[values.size()];
  int counter=0;
  double[] vs=new double[values.size()];
  for (  T v : values) {
    vs[counter]=v.doubleValue() + padding;
    counter+=1;
  }
  for (int i=0; i < period; i++) {
    s+=vs[i];
    b+=(vs[i + period] - vs[i]) / period;
  }
  s/=period;
  b/=period;
  last_s=s;
  if (Double.compare(s,0.0) == 0 || Double.compare(s,-0.0) == 0) {
    Arrays.fill(seasonal,0.0);
  }
 else {
    for (int i=0; i < period; i++) {
      seasonal[i]=vs[i] / s;
    }
  }
  for (int i=period; i < vs.length; i++) {
    if (seasonalityType.equals(SeasonalityType.MULTIPLICATIVE)) {
      s=alpha * (vs[i] / seasonal[i - period]) + (1.0d - alpha) * (last_s + last_b);
    }
 else {
      s=alpha * (vs[i] - seasonal[i - period]) + (1.0d - alpha) * (last_s + last_b);
    }
    b=beta * (s - last_s) + (1 - beta) * last_b;
    if (seasonalityType.equals(SeasonalityType.MULTIPLICATIVE)) {
      seasonal[i]=gamma * (vs[i] / (last_s + last_b)) + (1 - gamma) * seasonal[i - period];
    }
 else {
      seasonal[i]=gamma * (vs[i] - (last_s - last_b)) + (1 - gamma) * seasonal[i - period];
    }
    last_s=s;
    last_b=b;
  }
  double[] forecastValues=new double[numForecasts];
  for (int i=1; i <= numForecasts; i++) {
    int idx=values.size() - period + ((i - 1) % period);
    if (seasonalityType.equals(SeasonalityType.MULTIPLICATIVE)) {
      forecastValues[i - 1]=(s + (i * b)) * seasonal[idx];
    }
 else {
      forecastValues[i - 1]=s + (i * b) + seasonal[idx];
    }
  }
  return forecastValues;
}
