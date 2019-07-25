@Override void analyze(Locals locals){
  if (write && Modifier.isFinal(field.modifiers)) {
    throw createError(new IllegalArgumentException("Cannot write to read-only field [" + field.name + "] for type [" + field.type.name + "]."));
  }
  actual=field.type;
}
