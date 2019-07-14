@GetMapping @Authorize(action=Permission.ACTION_QUERY) @ApiOperation("??????") public ResponseMessage<PagerResult<Model>> getModelList(QueryParamEntity param){
  ModelQuery modelQuery=repositoryService.createModelQuery();
  return ResponseMessage.ok(QueryUtils.doQuery(modelQuery,param,model -> FastBeanCopier.copy(model,new ModelEntity()),(term,modelQuery1) -> {
    if ("latestVersion".equals(term.getColumn())) {
      modelQuery1.latestVersion();
    }
  }
));
}
