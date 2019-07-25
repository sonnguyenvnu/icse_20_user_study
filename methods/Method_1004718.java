@RequestMapping(path={"/spring","/spring.zip"}) public String spring(){
  String url=this.metadataProvider.get().createCliDistributionURl("zip");
  return "redirect:" + url;
}
