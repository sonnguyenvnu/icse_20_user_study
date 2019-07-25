private ExportTemplate export(String templateId){
  RegisteredTemplate template=registeredTemplateService.findRegisteredTemplate(new RegisteredTemplateRequest.Builder().templateId(templateId).nifiTemplateId(templateId).includeSensitiveProperties(true).build());
  if (template != null) {
    List<String> connectingReusableTemplates=new ArrayList<>();
    Set<String> connectedTemplateIds=new HashSet<>();
    Set<ReusableTemplateConnectionInfo> outputPortConnectionMetadata=new HashSet<>();
    Set<RemoteProcessGroupInputPort> templateRemoteInputPorts=new HashSet<>();
    List<ReusableTemplateConnectionInfo> reusableTemplateConnectionInfos=null;
    if (template.usesReusableTemplate()) {
      reusableTemplateConnectionInfos=template.getReusableTemplateConnections();
    }
    List<ReusableTemplateConnectionInfo> remoteProcessGroupConnectionInfo=getRemoteProcessGroupConnectionInfo(template.getNifiTemplate());
    if (reusableTemplateConnectionInfos != null) {
      reusableTemplateConnectionInfos.addAll(remoteProcessGroupConnectionInfo);
    }
 else {
      reusableTemplateConnectionInfos=remoteProcessGroupConnectionInfo;
    }
    if (reusableTemplateConnectionInfos != null && !reusableTemplateConnectionInfos.isEmpty()) {
      ProcessGroupFlowDTO reusableTemplateFlow=templateConnectionUtil.getReusableTemplateCategoryProcessGroupFlow();
      Map<String,PortDTOWithGroupInfo> reusableTemplatePorts=templateConnectionUtil.getReusableFeedInputPorts(reusableTemplateFlow).stream().collect(Collectors.toMap(port -> port.getName(),port -> port));
      reusableTemplateConnectionInfos.stream().filter(connectionInfo -> StringUtils.isBlank(connectionInfo.getReusableTemplateProcessGroupName())).forEach(connectionInfo -> {
        PortDTOWithGroupInfo port=reusableTemplatePorts.get(connectionInfo.getReusableTemplateInputPortName());
        if (port != null) {
          connectionInfo.setReusableTemplateProcessGroupName(port.getDestinationProcessGroupName());
        }
      }
);
      if (reusableTemplateFlow != null) {
        gatherConnectedReusableTemplates(connectingReusableTemplates,connectedTemplateIds,outputPortConnectionMetadata,reusableTemplateConnectionInfos,reusableTemplateFlow);
      }
      if (isRemoteProcessGroupsEnabled()) {
        reusableTemplateConnectionInfos.stream().forEach(connectionInfo -> {
          Set<RemoteProcessGroupInputPort> remoteProcessGroupInputPorts=findReusableTemplateRemoteInputPorts(reusableTemplateFlow,connectionInfo.getReusableTemplateProcessGroupName());
          templateRemoteInputPorts.addAll(remoteProcessGroupInputPorts);
        }
);
      }
    }
    String templateXml=null;
    try {
      if (template != null) {
        try {
          templateXml=nifiRestClient.getTemplateXml(template.getNifiTemplateId());
        }
 catch (        NifiClientRuntimeException e) {
          TemplateDTO templateDTO=nifiRestClient.getTemplateByName(template.getTemplateName());
          if (templateDTO != null) {
            templateXml=nifiRestClient.getTemplateXml(templateDTO.getId());
          }
        }
      }
    }
 catch (    NifiConnectionException e) {
      throw e;
    }
catch (    Exception e) {
      throw new TemplateExportException("Unable to find Nifi Template for " + templateId);
    }
    byte[] zipFile=zip(template,templateXml,connectingReusableTemplates,outputPortConnectionMetadata,templateRemoteInputPorts);
    return new ExportTemplate(SystemNamingService.generateSystemName(template.getTemplateName()) + ".template.zip",template.getTemplateName(),template.getDescription(),template.isStream(),zipFile);
  }
 else {
    throw new TemplateExportException("Unable to find Template for " + templateId);
  }
}
