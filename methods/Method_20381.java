/** 
 * For testing. 
 */
public static EpoxyProcessor withNoValidation(){
  HashMap<String,String> options=new HashMap<>();
  options.put(PROCESSOR_OPTION_VALIDATE_MODEL_USAGE,"false");
  return new EpoxyProcessor(options);
}
