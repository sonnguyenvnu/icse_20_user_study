protected Connector findConnector(String connectorName){
  List<Connector> connectors=org.eclipse.jdi.Bootstrap.virtualMachineManager().allConnectors();
  for (  Object c : connectors) {
    Connector connector=(Connector)c;
    if (connector.name().equals(connectorName)) {
      return connector;
    }
  }
  Messages.showError("Compiler Error","findConnector() failed to find " + connectorName + " inside Runner",null);
  return null;
}
