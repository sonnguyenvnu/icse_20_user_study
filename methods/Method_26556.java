Type inlineTypeVar(UTypeVar var) throws CouldNotResolveImportException {
  Optional<TypeWithExpression> typeVarBinding=getOptionalBinding(var.key());
  if (typeVarBinding.isPresent()) {
    return typeVarBinding.get().type();
  }
 else {
    return inlineAsVar(var);
  }
}
