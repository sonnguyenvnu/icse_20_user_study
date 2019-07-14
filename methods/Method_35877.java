public boolean keyEquals(String candidateKey){
  return CaseInsensitiveKey.from(candidateKey).equals(caseInsensitiveKey());
}
