@Override public void validated(VerificationCode code){
  VerificationCode verificationCode=verificationCodeRepository.findByScenesAndTypeAndValueAndStatusIsTrue(code.getScenes(),code.getType(),code.getValue());
  if (verificationCode == null || !verificationCode.getCode().equals(code.getCode())) {
    throw new BadRequestException("?????");
  }
 else {
    verificationCode.setStatus(false);
    verificationCodeRepository.save(verificationCode);
  }
}
