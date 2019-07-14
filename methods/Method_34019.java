protected void resetPreviousAuthentication(Authentication previousAuthentication){
  SecurityContextHolder.getContext().setAuthentication(previousAuthentication);
}
