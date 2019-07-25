public GeneratorSettings with(FormatSchema sch){
  return (schema == sch) ? this : new GeneratorSettings(prettyPrinter,sch,characterEscapes,rootValueSeparator);
}
