@Override public IndexFactory add(String indexName,String typeName,String id,JSONObject object) throws IOException {
  if (this.elasticIndexFactory == null && this.elastic_address != null) {
    connectElasticsearch(this.elastic_address);
  }
  if (this.elasticIndexFactory == null) {
    this.elastic_address=null;
  }
 else   try {
    this.elasticIndexFactory.getIndex().add(indexName,typeName,id,object);
    return this.elasticIndexFactory;
  }
 catch (  IOException e) {
    Data.logger.debug("Index/Client: add elastic service '" + this.elasticIndexFactory.getConnectionURL() + "', elastic fail",e);
  }
  if (this.mcpIndexFactory == null && this.mcp_host != null) {
    connectMCP(this.mcp_host,this.mcp_port);
    if (this.mcpIndexFactory == null) {
      Data.logger.warn("Index/Client: FATAL: connection to MCP lost!");
    }
  }
  if (this.mcpIndexFactory != null)   try {
    this.mcpIndexFactory.getIndex().add(indexName,typeName,id,object);
    return this.mcpIndexFactory;
  }
 catch (  IOException e) {
    Data.logger.debug("Index/Client: add mcp service '" + mcp_host + "',mcp fail",e);
  }
  throw new IOException("Index/Client: add mcp service: no factory found!");
}
