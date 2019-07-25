@Override public boolean exist(String indexName,String typeName,String id) throws IOException {
  if (this.elasticIndexFactory == null && this.elastic_address != null) {
    connectElasticsearch(this.elastic_address);
  }
  if (this.elasticIndexFactory == null) {
    this.elastic_address=null;
  }
 else   try {
    boolean exist=this.elasticIndexFactory.getIndex().exist(indexName,typeName,id);
    return exist;
  }
 catch (  IOException e) {
    Data.logger.debug("Index/Client: exist elastic service '" + this.elasticIndexFactory.getConnectionURL() + "', elastic fail",e);
  }
  if (this.mcpIndexFactory == null && this.mcp_host != null) {
    connectMCP(this.mcp_host,this.mcp_port);
    if (this.mcpIndexFactory == null) {
      Data.logger.warn("Index/Client: FATAL: connection to MCP lost!");
    }
  }
  if (this.mcpIndexFactory != null)   try {
    boolean exist=this.mcpIndexFactory.getIndex().exist(indexName,typeName,id);
    return exist;
  }
 catch (  IOException e) {
    Data.logger.debug("Index/Client: exist mcp service '" + mcp_host + "',mcp fail",e);
  }
  throw new IOException("Index/Client: exist mcp service: no factory found!");
}
