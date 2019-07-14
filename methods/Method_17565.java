private static void registerIrr(Map<String,Function<Config,Set<Policy>>> factories){
  factories.put("irr.Frd",FrdPolicy::policies);
  factories.put("irr.IndicatorFrd",IndicatorFrdPolicy::policies);
  factories.put("irr.ClimberFrd",HillClimberFrdPolicy::policies);
  factories.put("irr.Lirs",LirsPolicy::policies);
  factories.put("irr.ClockPro",ClockProPolicy::policies);
}
