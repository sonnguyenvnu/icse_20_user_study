@GetMapping("/condition") public CommonResult<ProductConditionBO> condition(@RequestParam(value="keyword",required=false) String keyword){
  ProductConditionDTO productConditionDTO=new ProductConditionDTO().setKeyword(keyword).setFields(Collections.singleton(ProductConditionDTO.FIELD_CATEGORY));
  return success(productSearchService.getSearchCondition(productConditionDTO));
}
