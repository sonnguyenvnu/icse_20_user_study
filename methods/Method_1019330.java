/** 
 * Apply templates.
 */
private void generate(TemplateFile input,List<OutputFile> outputs,TemplateOptions templateOptions) throws IOException {
  final String targetFileName=targetFileName(templatesPath.relativize(input.path).toString(),templateOptions);
  final OutputFile output=new OutputFile(outputPath.resolve(targetFileName).toAbsolutePath().normalize());
  if (incremental && Files.exists(output.path) && Files.getLastModifiedTime(output.path).toMillis() >= Files.getLastModifiedTime(input.path).toMillis()) {
    output.upToDate=true;
    outputs.add(output);
    return;
  }
  String template=new String(Files.readAllBytes(input.path),StandardCharsets.UTF_8);
  timeVelocity.start();
  template=filterVelocity(input,template,templateOptions);
  timeVelocity.stop();
  if (templateOptions.isIgnored()) {
    return;
  }
  getLog().debug("Processing: " + input.getFileName() + " => " + output.path);
  try {
    timeIntrinsics.start();
    template=filterIntrinsics(template,templateOptions);
    timeIntrinsics.stop();
    timeComments.start();
    template=filterComments(template);
    timeComments.stop();
    timeTypeClassRefs.start();
    template=filterTypeClassRefs(template,templateOptions);
    template=filterStaticTokens(template,templateOptions);
    timeTypeClassRefs.stop();
  }
 catch (  RuntimeException e) {
    getLog().error("Error processing: " + input.getFileName() + " => " + output.path + ":\n\t" + e.getMessage());
    throw e;
  }
  Files.createDirectories(output.path.getParent());
  Files.write(output.path,template.getBytes(StandardCharsets.UTF_8));
  outputs.add(output);
}
