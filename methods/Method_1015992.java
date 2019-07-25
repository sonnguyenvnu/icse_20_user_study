@Override public JSONObject query(String indexName,String typeName,String id) throws IOException {
  if (this.elasticIndexFactory == null && this.elastic_address != null) {
    connectElasticsearch(this.elastic_address);
  }
  if (this.elasticIndexFactory == null) {
    this.elastic_address=null;
  }
 else   try {
    JSONObject json=this.elasticIndexFactory.getIndex().query(indexName,typeName,id);
    return json;
  }
 catch (  IOException e) {
    Data.logger.debug("Index/Client: query elastic service '" + this.elasticIndexFactory.getConnectionURL() + "', elastic fail",e);
  }
  if (this.mcpIndexFactory == null && this.mcp_host != null) {
    connectMCP(this.mcp_host,this.mcp_port);
    if (this.mcpIndexFactory == null) {
      Data.logger.warn("Index/Client: FATAL: connection to MCP lost!");
    }
  }
  if (this.mcpIndexFactory != null)   try {
    JSONObject json=this.mcpIndexFactory.getIndex().query(indexName,typeName,id);
    return json;
  }
 catch (  IOException e) {
    Data.logger.debug("Index/Client: query mcp service '" + mcp_host + "',mcp fail",e);
  }
  throw new IOException("Index/Client: query mcp service: no factory found!");
}
