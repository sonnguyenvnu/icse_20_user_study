private byte[] zip(RegisteredTemplate template,String nifiTemplateXml,List<String> reusableTemplateXmls,Set<ReusableTemplateConnectionInfo> outputPortMetadata,Set<RemoteProcessGroupInputPort> reusableTemplateRemoteInputPorts){
  if (nifiTemplateXml == null) {
    throw new NifiConnectionException("Attempt to export using invalid template");
  }
  ByteArrayOutputStream baos=new ByteArrayOutputStream();
  try (ZipOutputStream zos=new ZipOutputStream(baos)){
    ZipEntry entry=new ZipEntry(ImportTemplate.NIFI_TEMPLATE_XML_FILE);
    zos.putNextEntry(entry);
    zos.write(nifiTemplateXml.getBytes());
    zos.closeEntry();
    int reusableTemplateNumber=0;
    for (    String reusableTemplateXml : reusableTemplateXmls) {
      entry=new ZipEntry(String.format("%s_%s.xml",ImportTemplate.NIFI_CONNECTING_REUSABLE_TEMPLATE_XML_FILE,reusableTemplateNumber++));
      zos.putNextEntry(entry);
      zos.write(reusableTemplateXml.getBytes());
      zos.closeEntry();
    }
    entry=new ZipEntry(ImportTemplate.TEMPLATE_JSON_FILE);
    zos.putNextEntry(entry);
    String json=ObjectMapperSerializer.serialize(template);
    zos.write(json.getBytes());
    zos.closeEntry();
    if (outputPortMetadata != null && !outputPortMetadata.isEmpty()) {
      entry=new ZipEntry(ImportTemplate.REUSABLE_TEMPLATE_OUTPUT_CONNECTION_FILE);
      zos.putNextEntry(entry);
      json=ObjectMapperSerializer.serialize(outputPortMetadata);
      zos.write(json.getBytes());
      zos.closeEntry();
    }
    if (reusableTemplateRemoteInputPorts != null && !reusableTemplateRemoteInputPorts.isEmpty()) {
      entry=new ZipEntry(ImportTemplate.REUSABLE_TEMPLATE_REMOTE_INPUT_PORT_JSON_FILE);
      zos.putNextEntry(entry);
      json=ObjectMapperSerializer.serialize(reusableTemplateRemoteInputPorts);
      zos.write(json.getBytes());
      zos.closeEntry();
    }
  }
 catch (  IOException ioe) {
    throw new RuntimeException(ioe);
  }
  return baos.toByteArray();
}
