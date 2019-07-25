public GeneratorSettings with(CharacterEscapes esc){
  return (characterEscapes == esc) ? this : new GeneratorSettings(prettyPrinter,schema,esc,rootValueSeparator);
}
