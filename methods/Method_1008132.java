public void validate(KeyspaceMetadata ksm,CFMetaData cfm) throws ConfigurationException {
  ColumnDefinition cd=cfm.getColumnDefinition(new ColumnIdentifier(name,true));
  if (cd == null)   return;
  exists=true;
  if (cd.kind != this.kind && this.kind != Kind.REGULAR)   throw new ConfigurationException("Bad mapping, column [" + this.name + "] kind [" + this.kind + "] does not match the existing one type [" + cd.kind + "]");
  AbstractType<?> cql3Type=this.type.prepare(ksm).getType();
  if (!cd.type.isCollection()) {
    if (cql3Type.toString().equals("frozen<geo_point>")) {
      if (!(cql3Type.toString().equals("text") || cql3Type.toString().equals("frozen<geo_point>"))) {
        throw new ConfigurationException("geo_point cannot be mapped to column [" + this.name + "] with CQL type [" + this.type + "]. ");
      }
    }
 else {
      String inferedCql=cql3Type.asCQL3Type().toString();
      String existingCql3=cd.type.asCQL3Type().toString();
      if (!existingCql3.equals(inferedCql) && !(existingCql3.endsWith("uuid") && inferedCql.equals("text")) && !(existingCql3.equals("timeuuid") && (inferedCql.equals("timestamp") || inferedCql.equals("text"))) && !(existingCql3.equals("decimal") && inferedCql.equals("text")) && !(existingCql3.equals("date") && inferedCql.equals("timestamp")) && !(existingCql3.equals("time") && inferedCql.equals("bigint")))       throw new ConfigurationException("Existing column [" + this.name + "] type [" + existingCql3 + "] mismatch with inferred type [" + inferedCql + "]");
    }
  }
}
