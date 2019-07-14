public UpbitBalances getWallet() throws UpbitException, IOException {
  UpbitBalances upbitBalances=upbit.getWallet(this.signatureCreator);
  return upbitBalances;
}
