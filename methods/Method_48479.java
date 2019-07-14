private long getIdentifier(final long schemaId,final SystemRelationType type,final Direction dir){
  int edgeDir=EdgeDirection.position(dir);
  assert edgeDir == 0 || edgeDir == 1;
  long typeId=(schemaId >>> SCHEMAID_BACK_SHIFT);
  int systemTypeId;
  if (type == BaseLabel.SchemaDefinitionEdge)   systemTypeId=0;
 else   if (type == BaseKey.SchemaName)   systemTypeId=1;
 else   if (type == BaseKey.SchemaCategory)   systemTypeId=2;
 else   if (type == BaseKey.SchemaDefinitionProperty)   systemTypeId=3;
 else   throw new AssertionError("Unexpected SystemType encountered in StandardSchemaCache: " + type.name());
  assert (systemTypeId < (1 << 2));
  return (((typeId << 2) + systemTypeId) << 1) + edgeDir;
}
