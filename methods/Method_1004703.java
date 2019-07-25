/** 
 * Expand the link using the specified parameters.
 * @param parameters the parameters value
 * @return an URI where all variables have been expanded
 */
public URI expand(Map<String,String> parameters){
  AtomicReference<String> result=new AtomicReference<>(this.href);
  this.templateVariables.forEach((var) -> {
    Object value=parameters.get(var);
    if (value == null) {
      throw new IllegalArgumentException("Could not expand " + this.href + ", missing value for '" + var + "'");
    }
    result.set(result.get().replace("{" + var + "}",value.toString()));
  }
);
  try {
    return new URI(result.get());
  }
 catch (  URISyntaxException ex) {
    throw new IllegalStateException("Invalid URL",ex);
  }
}
