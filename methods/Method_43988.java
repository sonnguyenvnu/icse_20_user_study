private static int pauseAndIncrease(int retryDelaySec){
  pause(retryDelaySec);
  retryDelaySec=Math.min(retryDelaySec * 2 - (int)(retryDelaySec * 0.75),3600);
  return retryDelaySec;
}
