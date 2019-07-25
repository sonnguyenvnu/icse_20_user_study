/** 
 * Load from configuration.
 */
protected final void load(){
  if (!confFolder.exists()) {
    return;
  }
  if (!confFile.exists()) {
    return;
  }
  try {
    JsonObject json=Json.parse(FileIO.readFile(confFile.getAbsolutePath())).asObject();
    for (    Field field : Reflect.fields(getClass())) {
      String name=getValueName(field);
      if (name == null) {
        continue;
      }
      JsonValue value=json.get(name);
      if (value == null) {
        continue;
      }
      try {
        if (field.getType().equals(boolean.class)) {
          field.set(this,value.asBoolean());
        }
 else         if (field.getType().equals(int.class)) {
          field.set(this,value.asInt());
        }
 else         if (field.getType().equals(long.class)) {
          field.set(this,value.asLong());
        }
 else         if (field.getType().equals(float.class)) {
          field.set(this,value.asFloat());
        }
 else         if (field.getType().equals(double.class)) {
          field.set(this,value.asDouble());
        }
 else         if (field.getType().equals(String.class)) {
          field.set(this,value.asString());
        }
 else {
          Object parsed=parse(field.getType(),value);
          if (parsed != null) {
            field.set(this,parsed);
          }
        }
      }
 catch (      Exception e) {
        Logging.error("Skipping bad option: " + confFile.getName() + " - " + name);
      }
    }
  }
 catch (  Exception e) {
    Logging.error(e);
  }
}
