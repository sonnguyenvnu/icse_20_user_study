@Override public void collect(@NotNull TwigFileVariableCollectorParameter parameter,@NotNull Map<String,Set<String>> variables){
  variables.put("app",new HashSet<>(Collections.singletonList("\\Symfony\\Bundle\\FrameworkBundle\\Templating\\GlobalVariables")));
}
