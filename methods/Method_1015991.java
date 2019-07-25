@Override public long count(String indexName,String typeName,QueryLanguage language,String query) throws IOException {
  if (this.elasticIndexFactory == null && this.elastic_address != null) {
    connectElasticsearch(this.elastic_address);
  }
  if (this.elasticIndexFactory == null) {
    this.elastic_address=null;
  }
 else   try {
    long count=this.elasticIndexFactory.getIndex().count(indexName,typeName,language,query);
    return count;
  }
 catch (  IOException e) {
    Data.logger.debug("Index/Client: count elastic service '" + this.elasticIndexFactory.getConnectionURL() + "', elastic fail",e);
  }
  if (this.mcpIndexFactory == null && this.mcp_host != null) {
    connectMCP(this.mcp_host,this.mcp_port);
    if (this.mcpIndexFactory == null) {
      Data.logger.warn("Index/Client: FATAL: connection to MCP lost!");
    }
  }
  if (this.mcpIndexFactory != null)   try {
    long count=this.mcpIndexFactory.getIndex().count(indexName,typeName,language,query);
    return count;
  }
 catch (  IOException e) {
    Data.logger.debug("Index/Client: count mcp service '" + mcp_host + "',mcp fail",e);
  }
  throw new IOException("Index/Client: count mcp service: no factory found!");
}
