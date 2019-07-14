private Frequency makeSketch(Config config){
  BasicSettings settings=new BasicSettings(config);
  String type=settings.tinyLfu().sketch();
  if (type.equalsIgnoreCase("count-min-4")) {
    String reset=settings.tinyLfu().countMin4().reset();
    if (reset.equalsIgnoreCase("periodic")) {
      return new PeriodicResetCountMin4(config);
    }
 else     if (reset.equalsIgnoreCase("incremental")) {
      return new IncrementalResetCountMin4(config);
    }
 else     if (reset.equalsIgnoreCase("climber")) {
      return new ClimberResetCountMin4(config);
    }
 else     if (reset.equalsIgnoreCase("indicator")) {
      return new IndicatorResetCountMin4(config);
    }
  }
 else   if (type.equalsIgnoreCase("count-min-64")) {
    return new CountMin64TinyLfu(config);
  }
 else   if (type.equalsIgnoreCase("random-table")) {
    return new RandomRemovalFrequencyTable(config);
  }
 else   if (type.equalsIgnoreCase("tiny-table")) {
    return new TinyCacheAdapter(config);
  }
 else   if (type.equalsIgnoreCase("perfect-table")) {
    return new PerfectFrequency(config);
  }
  throw new IllegalStateException("Unknown sketch type: " + type);
}
