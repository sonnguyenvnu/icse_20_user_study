@DELETE @Path("/{jobName}/sharding/{item}/disable") @Consumes(MediaType.APPLICATION_JSON) public void enableSharding(@PathParam("jobName") final String jobName,@PathParam("item") final String item){
  jobAPIService.getShardingOperateAPI().enable(jobName,item);
}
