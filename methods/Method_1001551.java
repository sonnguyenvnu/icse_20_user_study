public static Command create(double scale){
  return new CommandCreoleMath("^(?i)(" + Splitter.mathPattern + ")",scale);
}
