/** 
 * Write the provided schema as the top level type in a .pdl file.
 * @param schema provides the schema to encode to .pdl and emit to this instance's writer.
 * @throws IOException if a writer IO exception occurs.
 */
@Override public void encode(DataSchema schema) throws IOException {
  if (_typeReferenceFormat != TypeReferenceFormat.DENORMALIZE) {
    _importsByLocalName=computeImports(schema);
  }
 else {
    _importsByLocalName=Collections.emptyMap();
  }
  if (schema instanceof NamedDataSchema) {
    NamedDataSchema namedSchema=(NamedDataSchema)schema;
    boolean hasNamespace=StringUtils.isNotBlank(namedSchema.getNamespace());
    boolean hasPackage=StringUtils.isNotBlank(namedSchema.getPackage());
    if (hasNamespace || hasPackage) {
      if (hasNamespace) {
        writeLine("namespace " + escapeIdentifier(namedSchema.getNamespace()));
        _namespace=namedSchema.getNamespace();
      }
      if (hasPackage) {
        writeLine("package " + escapeIdentifier(namedSchema.getPackage()));
        _package=namedSchema.getPackage();
      }
      newline();
    }
  }
  if (_importsByLocalName.size() > 0) {
    for (    Name importName : new TreeSet<>(_importsByLocalName.values())) {
      if (!importName.getNamespace().equals(_namespace)) {
        writeLine("import " + escapeIdentifier(importName.getFullName()));
      }
    }
    newline();
  }
  writeInlineSchema(schema);
}
