@Override protected InExRules<String,String,Pattern> createRulesEngine(){
  return new InExRules<String,String,Pattern>(){
    @Override protected Pattern makeRule(    final String rule){
      return Pattern.compile(rule);
    }
    @Override public boolean accept(    final String path,    final Pattern pattern,    final boolean include){
      return pattern.matcher(path).matches();
    }
  }
;
}
