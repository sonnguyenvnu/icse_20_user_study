@RequestMapping("/logout") @ResponseStatus(HttpStatus.NO_CONTENT) public void logout(HttpSession session){
  session.invalidate();
}
