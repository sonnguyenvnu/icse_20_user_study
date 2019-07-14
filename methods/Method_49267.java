public static ConsistencyModifier getConsistencyModifier(SchemaSource schema){
  return getTypeModifier(schema,ModifierType.CONSISTENCY,ConsistencyModifier.DEFAULT);
}
