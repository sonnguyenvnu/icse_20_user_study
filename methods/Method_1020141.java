@GetMapping("/time/now") @ResponseBody public SeckillResult<Long> time(){
  Date now=new Date();
  return new SeckillResult<Long>(true,now.getTime());
}
