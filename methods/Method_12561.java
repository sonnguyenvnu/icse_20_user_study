@ResponseBody @RequestMapping(path=APPLICATION_MAPPED_PATH,method={RequestMethod.GET,RequestMethod.HEAD,RequestMethod.POST,RequestMethod.PUT,RequestMethod.PATCH,RequestMethod.DELETE,RequestMethod.OPTIONS}) public Flux<InstanceWebProxy.InstanceResponse> endpointProxy(@PathVariable("applicationName") String applicationName,HttpServletRequest servletRequest){
  ServerHttpRequest request=new ServletServerHttpRequest(servletRequest);
  String endpointLocalPath=this.getEndpointLocalPath(this.adminContextPath + APPLICATION_MAPPED_PATH,servletRequest);
  URI uri=UriComponentsBuilder.fromPath(endpointLocalPath).query(request.getURI().getRawQuery()).build(true).toUri();
  Flux<DataBuffer> cachedBody=DataBufferUtils.readInputStream(request::getBody,this.bufferFactory,4096).cache();
  return this.instanceWebProxy.forward(this.registry.getInstances(applicationName),uri,request.getMethod(),this.httpHeadersFilter.filterHeaders(request.getHeaders()),BodyInserters.fromDataBuffers(cachedBody));
}
