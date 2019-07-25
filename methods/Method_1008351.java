@Override public Boolean visit(TypeConverterBinding command){
  injector.state.addConverter(new MatcherAndConverter(command.getTypeMatcher(),command.getTypeConverter(),command.getSource()));
  return true;
}
