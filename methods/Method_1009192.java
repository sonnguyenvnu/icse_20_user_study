/** 
 * Whether the decompiler accepts the  {@code macroNameArity}
 * @return {@code true} if {@link #append(StringBuilder,org.elixir_lang.beam.MacroNameArity)} should be called with{@code macroNameArity}.
 */
@Override public boolean accept(@NotNull org.elixir_lang.beam.MacroNameArity macroNameArity){
  Integer arity=macroNameArity.arity;
  return arity != null && arity == 2 && isInfixOperator(macroNameArity.name);
}
