private Toolkit createToolkit(String toolkitUrl,Properties props){
  try {
    return ToolkitFactory.createToolkit(toolkitUrl,props);
  }
 catch (  ToolkitInstantiationException e) {
    throw new RuntimeException(e);
  }
}
