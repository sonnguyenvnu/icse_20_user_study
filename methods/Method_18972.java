/** 
 * Saves the prop names of the given spec model at a well-known path within the resources. 
 */
public void saveNames(SpecModel specModel) throws IOException {
  if (specModel.getRawProps().isEmpty()) {
    return;
  }
  final FileObject outputFile=mFiler.createResource(StandardLocation.CLASS_OUTPUT,"",BASE_PATH + specModel.getSpecTypeName() + FILE_EXT);
  try (Writer writer=new BufferedWriter(new OutputStreamWriter(outputFile.openOutputStream()))){
    for (    final PropModel propModel : specModel.getRawProps()) {
      writer.write(propModel.getName() + "\n");
    }
    for (    final InjectPropModel propModel : specModel.getRawInjectProps()) {
      writer.write(propModel.getName() + "\n");
    }
  }
 }
