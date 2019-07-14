public void customize(SSLEngine sslEngine){
  SSLParameters sslParams=sslEngine.getSSLParameters();
  sslEngine.setSSLParameters(sslParams);
  if (super.getWantClientAuth())   sslEngine.setWantClientAuth(super.getWantClientAuth());
  if (super.getNeedClientAuth())   sslEngine.setNeedClientAuth(super.getNeedClientAuth());
  sslEngine.setEnabledCipherSuites(super.selectCipherSuites(sslEngine.getEnabledCipherSuites(),sslEngine.getSupportedCipherSuites()));
  sslEngine.setEnabledProtocols(super.selectProtocols(sslEngine.getEnabledProtocols(),sslEngine.getSupportedProtocols()));
}
