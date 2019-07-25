@RequestMapping(path="/metadata/config",produces="application/json") @ResponseBody public InitializrMetadata config(){
  return this.metadataProvider.get();
}
