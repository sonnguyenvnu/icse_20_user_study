/** 
 * Validate a schema/instance pair <p>The third boolean argument instructs the validator as to whether it should validate children even if the container (array or object) fails to validate.</p>
 * @param schema the schema
 * @param instance the instance
 * @param deepCheck see description
 * @return a validation report
 * @throws ProcessingException an exception occurred during validation
 * @throws NullPointerException the schema or instance is null
 * @since 2.1.8
 */
public ProcessingReport validate(final JsonNode schema,final JsonNode instance,final boolean deepCheck) throws ProcessingException {
  final ProcessingReport report=reportProvider.newReport();
  final FullData data=buildData(schema,instance,deepCheck);
  return ProcessingResult.of(processor,report,data).getReport();
}
