@Override public void intercept(Invocation inv){
  if (!config.isConfigOk()) {
    inv.invoke();
    return;
  }
  Timer.Context timerContext=null;
  EnableMetricCounter counterAnnotation=inv.getMethod().getAnnotation(EnableMetricCounter.class);
  if (counterAnnotation != null) {
    String value=AnnotationUtil.get(counterAnnotation.value());
    String name=StrUtil.isBlank(value) ? inv.getController().getClass().getName() + "." + inv.getMethodName() + ".counter" : value;
    Counter counter=Jboot.getMetric().counter(name);
    counter.inc();
  }
  Counter concurrencyRecord=null;
  EnableMetricConcurrency concurrencyAnnotation=inv.getMethod().getAnnotation(EnableMetricConcurrency.class);
  if (concurrencyAnnotation != null) {
    String value=AnnotationUtil.get(concurrencyAnnotation.value());
    String name=StrUtil.isBlank(value) ? inv.getController().getClass().getName() + "." + inv.getMethodName() + ".concurrency" : value;
    concurrencyRecord=Jboot.getMetric().counter(name);
    concurrencyRecord.inc();
  }
  EnableMetricMeter meterAnnotation=inv.getMethod().getAnnotation(EnableMetricMeter.class);
  if (meterAnnotation != null) {
    String value=AnnotationUtil.get(meterAnnotation.value());
    String name=StrUtil.isBlank(value) ? inv.getController().getClass().getName() + "." + inv.getMethodName() + ".meter" : value;
    Meter meter=Jboot.getMetric().meter(name);
    meter.mark();
  }
  EnableMetricHistogram histogramAnnotation=inv.getMethod().getAnnotation(EnableMetricHistogram.class);
  if (histogramAnnotation != null) {
    String value=AnnotationUtil.get(histogramAnnotation.value());
    String name=StrUtil.isBlank(value) ? inv.getController().getClass().getName() + "." + inv.getMethodName() + ".histogram" : value;
    Histogram histogram=Jboot.getMetric().histogram(name);
    histogram.update(histogramAnnotation.update());
  }
  EnableMetricTimer timerAnnotation=inv.getMethod().getAnnotation(EnableMetricTimer.class);
  if (timerAnnotation != null) {
    String value=AnnotationUtil.get(timerAnnotation.value());
    String name=StrUtil.isBlank(value) ? inv.getController().getClass().getName() + "." + inv.getMethodName() + ".timer" : value;
    Timer timer=Jboot.getMetric().timer(name);
    timerContext=timer.time();
  }
  try {
    inv.invoke();
  }
  finally {
    if (concurrencyRecord != null) {
      concurrencyRecord.dec();
    }
    if (timerContext != null) {
      timerContext.stop();
    }
  }
}
