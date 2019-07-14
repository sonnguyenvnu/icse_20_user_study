/** 
 * Converts Flyway-specific environment variables to their matching properties.
 * @return The properties corresponding to the environment variables.
 */
public static Map<String,String> environmentVariablesToPropertyMap(){
  Map<String,String> result=new HashMap<>();
  for (  Map.Entry<String,String> entry : System.getenv().entrySet()) {
    String convertedKey=convertKey(entry.getKey());
    if (convertedKey != null) {
      result.put(convertKey(entry.getKey()),entry.getValue());
    }
  }
  return result;
}
