public GeneratorSettings with(PrettyPrinter pp){
  return (pp == prettyPrinter) ? this : new GeneratorSettings(pp,schema,characterEscapes,rootValueSeparator);
}
