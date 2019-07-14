@POST @Path("/{jobName}/sharding/{item}/disable") @Consumes(MediaType.APPLICATION_JSON) public void disableSharding(@PathParam("jobName") final String jobName,@PathParam("item") final String item){
  jobAPIService.getShardingOperateAPI().disable(jobName,item);
}
