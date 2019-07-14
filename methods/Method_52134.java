@Override public Map<String,List<String>> getComparisonTargets(){
  Map<String,List<String>> rules=new HashMap<>();
  rules.put("<",Arrays.asList("0","1"));
  rules.put(">",Arrays.asList("0"));
  rules.put("==",Arrays.asList("0"));
  rules.put("!=",Arrays.asList("0"));
  rules.put(">=",Arrays.asList("0","1"));
  rules.put("<=",Arrays.asList("0"));
  return rules;
}
