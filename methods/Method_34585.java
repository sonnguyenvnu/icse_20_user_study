@Override public void initialize(){
  metricsRegistry.newGauge(createMetricName("name"),new Gauge<String>(){
    @Override public String value(){
      return key.name();
    }
  }
);
  metricsRegistry.newGauge(createMetricName("currentTime"),new Gauge<Long>(){
    @Override public Long value(){
      return System.currentTimeMillis();
    }
  }
);
  metricsRegistry.newGauge(createMetricName("threadActiveCount"),new Gauge<Number>(){
    @Override public Number value(){
      return metrics.getCurrentActiveCount();
    }
  }
);
  metricsRegistry.newGauge(createMetricName("completedTaskCount"),new Gauge<Number>(){
    @Override public Number value(){
      return metrics.getCurrentCompletedTaskCount();
    }
  }
);
  metricsRegistry.newGauge(createMetricName("largestPoolSize"),new Gauge<Number>(){
    @Override public Number value(){
      return metrics.getCurrentLargestPoolSize();
    }
  }
);
  metricsRegistry.newGauge(createMetricName("totalTaskCount"),new Gauge<Number>(){
    @Override public Number value(){
      return metrics.getCurrentTaskCount();
    }
  }
);
  metricsRegistry.newGauge(createMetricName("queueSize"),new Gauge<Number>(){
    @Override public Number value(){
      return metrics.getCurrentQueueSize();
    }
  }
);
  metricsRegistry.newGauge(createMetricName("rollingMaxActiveThreads"),new Gauge<Number>(){
    @Override public Number value(){
      return metrics.getRollingMaxActiveThreads();
    }
  }
);
  metricsRegistry.newGauge(createMetricName("countThreadsExecuted"),new Gauge<Number>(){
    @Override public Number value(){
      return metrics.getCumulativeCountThreadsExecuted();
    }
  }
);
  metricsRegistry.newGauge(createMetricName("rollingCountCommandsRejected"),new Gauge<Number>(){
    @Override public Number value(){
      try {
        return metrics.getRollingCount(HystrixRollingNumberEvent.THREAD_POOL_REJECTED);
      }
 catch (      NoSuchFieldError error) {
        logger.error("While publishing Yammer metrics, error looking up eventType for : rollingCountCommandsRejected.  Please check that all Hystrix versions are the same!");
        return 0L;
      }
    }
  }
);
  metricsRegistry.newGauge(createMetricName("rollingCountThreadsExecuted"),new Gauge<Number>(){
    @Override public Number value(){
      return metrics.getRollingCountThreadsExecuted();
    }
  }
);
  metricsRegistry.newGauge(createMetricName("propertyValue_corePoolSize"),new Gauge<Number>(){
    @Override public Number value(){
      return properties.coreSize().get();
    }
  }
);
  metricsRegistry.newGauge(createMetricName("propertyValue_maximumSize"),new Gauge<Number>(){
    @Override public Number value(){
      return properties.maximumSize().get();
    }
  }
);
  metricsRegistry.newGauge(createMetricName("propertyValue_actualMaximumSize"),new Gauge<Number>(){
    @Override public Number value(){
      return properties.actualMaximumSize();
    }
  }
);
  metricsRegistry.newGauge(createMetricName("propertyValue_keepAliveTimeInMinutes"),new Gauge<Number>(){
    @Override public Number value(){
      return properties.keepAliveTimeMinutes().get();
    }
  }
);
  metricsRegistry.newGauge(createMetricName("propertyValue_queueSizeRejectionThreshold"),new Gauge<Number>(){
    @Override public Number value(){
      return properties.queueSizeRejectionThreshold().get();
    }
  }
);
  metricsRegistry.newGauge(createMetricName("propertyValue_maxQueueSize"),new Gauge<Number>(){
    @Override public Number value(){
      return properties.maxQueueSize().get();
    }
  }
);
}
