static Counter build(CounterType type,int stripes){
switch (type) {
case AtomicLong:
    return new AtomicLongCounter();
case LongAdder:
  return new LongAdderCounter();
case FixedSizeStripedV6:
return new FixedSizeStripedCounter(createFixedSizeStripedCounterV6(stripes));
case FixedSizeStripedV8:
return new FixedSizeStripedCounter(createFixedSizeStripedCounterV8(stripes));
case CAT:
return new ConcurrentAutoTableCounter();
default :
throw new IllegalArgumentException();
}
}
