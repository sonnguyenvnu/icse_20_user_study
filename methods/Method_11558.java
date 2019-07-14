protected boolean shouldReserved(Request request){
  return request.getExtra(Request.CYCLE_TRIED_TIMES) != null;
}
