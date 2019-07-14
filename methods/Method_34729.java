/** 
 * Return the cached value if available. Otherwise, we need to synchronize access to the underlying  {@link Histogram}
 * @param percentile percentile of distribution
 * @return value at percentile (from cache if possible)
 */
public int getValueAtPercentile(double percentile){
  int permyriad=(int)(percentile * 100);
switch (permyriad) {
case 0:
    return p0;
case 500:
  return p5;
case 1000:
return p10;
case 1500:
return p15;
case 2000:
return p20;
case 2500:
return p25;
case 3000:
return p30;
case 3500:
return p35;
case 4000:
return p40;
case 4500:
return p45;
case 5000:
return p50;
case 5500:
return p55;
case 6000:
return p60;
case 6500:
return p65;
case 7000:
return p70;
case 7500:
return p75;
case 8000:
return p80;
case 8500:
return p85;
case 9000:
return p90;
case 9500:
return p95;
case 9900:
return p99;
case 9950:
return p99_5;
case 9990:
return p99_9;
case 9995:
return p99_95;
case 9999:
return p99_99;
case 10000:
return p100;
default :
throw new IllegalArgumentException("Percentile (" + percentile + ") is not currently cached");
}
}
