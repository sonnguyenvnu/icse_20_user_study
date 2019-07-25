/** 
 * Wehther the decompiler accepts the  {@code macroNameArity}.
 * @return {@code true} if {@link #append(StringBuilder,org.elixir_lang.beam.MacroNameArity)} should be called with{@code macroNameArity}.
 */
@Override public boolean accept(@NotNull org.elixir_lang.beam.MacroNameArity macroNameArity){
  return BARE_ATOM_PREDICATE.or(NOT_IDENTIFIER_OR_PREFIX_OPERATOR_PREDICATE).test(macroNameArity.name);
}
