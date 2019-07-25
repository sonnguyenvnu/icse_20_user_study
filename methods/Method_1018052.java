@Override public DataSource create(ID connId,String title){
  return this.connectorProvider.find(connId).map(conn -> {
    String systemName=generateSystemName(title);
    Path dsPath=MetadataPaths.dataSourcePath(conn.getSystemName(),systemName);
    if (JcrUtil.hasNode(getSession(),dsPath)) {
      throw DataSourceAlreadyExistsException.fromSystemName(title);
    }
 else {
      Node connNode=JcrUtil.createNode(getSession(),dsPath,JcrDataSource.NODE_TYPE);
      JcrDataSource dsrc=JcrUtil.createJcrObject(connNode,JcrDataSource.class);
      dsrc.setTitle(title);
      if (this.accessController.isEntityAccessControlled()) {
        final List<SecurityRole> roles=roleProvider.getEntityRoles(SecurityRole.DATASOURCE);
        actionsProvider.getAvailableActions(AllowedActions.DATASOURCE).ifPresent(actions -> dsrc.enableAccessControl((JcrAllowedActions)actions,JcrMetadataAccess.getActiveUser(),roles));
      }
 else {
        actionsProvider.getAvailableActions(AllowedActions.DATASOURCE).ifPresent(actions -> dsrc.disableAccessControl(JcrMetadataAccess.getActiveUser()));
      }
      return dsrc;
    }
  }
).orElseThrow(() -> new ConnectorNotFoundException(connId));
}
