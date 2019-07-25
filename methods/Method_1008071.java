@Override void analyze(Locals locals){
  actual=expected == null || explicit ? locals.getDefinition().DefType : expected;
}
