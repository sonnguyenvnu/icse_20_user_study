/** 
 * Returns a sequence of events based on the setting's distribution. 
 */
public static LongStream generate(BasicSettings settings){
  int events=settings.synthetic().events();
switch (settings.synthetic().distribution().toLowerCase(US)) {
case "counter":
    return counter(settings.synthetic().counter().start(),events);
case "exponential":
  return exponential(settings.synthetic().exponential().mean(),events);
case "hotspot":
HotspotSettings hotspot=settings.synthetic().hotspot();
return Synthetic.hotspot(hotspot.lowerBound(),hotspot.upperBound(),hotspot.hotOpnFraction(),hotspot.hotsetFraction(),events);
case "zipfian":
return zipfian(settings.synthetic().zipfian().items(),settings.synthetic().zipfian().constant(),events);
case "scrambled-zipfian":
return scrambledZipfian(settings.synthetic().zipfian().items(),settings.synthetic().zipfian().constant(),events);
case "skewed-zipfian-latest":
return skewedZipfianLatest(settings.synthetic().zipfian().items(),events);
case "uniform":
UniformSettings uniform=settings.synthetic().uniform();
return uniform(uniform.lowerBound(),uniform.upperBound(),events);
default :
throw new IllegalStateException("Unknown distribution: " + settings.synthetic().distribution());
}
}
