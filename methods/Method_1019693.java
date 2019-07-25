/** 
 * Serialize as XML the given  {@link FieldsMetadata} to the given XML writer. Here a sample of XML writer :<pre> <fields> <field name="project.Name" imageName="" listType="false" /> <field name="developers.Name" imageName="" listType="true" /> <field name="project.Logo" imageName="Logo" listType="false" /> </fields> </pre>
 * @param fieldsMetadata the metadata to serialize to XML.
 * @param writer the writer.
 * @param indent true if indent must be managed and false otherwise.
 * @throws IOException
 */
public void save(FieldsMetadata fieldsMetadata,Writer writer,boolean indent) throws IOException {
  save(fieldsMetadata,writer,null,indent,false);
}
