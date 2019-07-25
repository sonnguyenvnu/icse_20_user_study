@Override public ActionResult execute(){
  String recoveryEmailAddress=getNonNullRequestParamValue(Const.ParamsNames.SESSION_LINKS_RECOVERY_EMAIL);
  if (!StringHelper.isMatching(recoveryEmailAddress,REGEX_EMAIL)) {
    return new JsonResult("Invalid email address: " + recoveryEmailAddress,HttpStatusCodes.STATUS_CODE_BAD_REQUEST);
  }
  String userCaptchaResponse=getRequestParamValue(Const.ParamsNames.USER_CAPTCHA_RESPONSE);
  if (!new RecaptchaVerifier().isVerificationSuccessful(userCaptchaResponse)) {
    return new JsonResult(new SessionLinksRecoveryResponseData(false,"Something went wrong with " + "the reCAPTCHA verification. Please try again."));
  }
  EmailWrapper email=emailGenerator.generateSessionLinksRecoveryEmailForStudent(recoveryEmailAddress);
  EmailSendingStatus status=emailSender.sendEmail(email);
  if (status.isSuccess()) {
    return new JsonResult(new SessionLinksRecoveryResponseData(true,"The recovery links for your feedback sessions have been sent to the " + "specified email address: " + recoveryEmailAddress));
  }
 else {
    return new JsonResult(new SessionLinksRecoveryResponseData(false,"An error occurred. " + "The email could not be sent."));
  }
}
