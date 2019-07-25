@Override public JSONList query(String indexName,String typeName,QueryLanguage language,String query,int start,int count) throws IOException {
  if (this.elasticIndexFactory == null && this.elastic_address != null) {
    connectElasticsearch(this.elastic_address);
  }
  if (this.elasticIndexFactory == null) {
    this.elastic_address=null;
  }
 else   try {
    JSONList list=this.elasticIndexFactory.getIndex().query(indexName,typeName,language,query,start,count);
    return list;
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
    JSONList list=this.mcpIndexFactory.getIndex().query(indexName,typeName,language,query,start,count);
    return list;
  }
 catch (  IOException e) {
    Data.logger.debug("Index/Client: query mcp service '" + mcp_host + "',mcp fail",e);
  }
  throw new IOException("Index/Client: query mcp service: no factory found!");
}
