/** 
 * Load fields metadata in the given  {@link FieldsMetadata} from the given XML reader. Here a sample of XML reader :<pre> <fields> <field name="project.Name" imageName="" listType="false" /> <field name="developers.Name" imageName="" listType="true" /> <field name="project.Logo" imageName="Logo" listType="false" /> </fields> </pre>
 * @param inputStream the reader of the XML fields.
 * @throws SAXException
 * @throws IOException
 */
public FieldsMetadata load(Reader input) throws SAXException, IOException {
  XMLReader saxReader=XMLReaderFactory.createXMLReader();
  FieldsMetadataContentHandler myContentHandler=new FieldsMetadataContentHandler();
  saxReader.setContentHandler(myContentHandler);
  saxReader.parse(new InputSource(input));
  return myContentHandler.getFieldsMetadata();
}
