@GetMapping(value="/code/validated") public ResponseEntity validated(VerificationCode code){
  verificationCodeService.validated(code);
  return new ResponseEntity(HttpStatus.OK);
}
