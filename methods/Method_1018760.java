/** 
 * This function is used by non failover and failover cases. Even when we make a standard connection the server can provide us with its FO partner. If no FO information is available a standard connection is made. If the server returns a failover upon connection, we shall store the FO in our cache.
 */
private void login(String primary,String primaryInstanceName,int primaryPortNumber,String mirror,FailoverInfo foActual,int timeout,long timerStart) throws SQLServerException {
  final boolean isDBMirroring=null != mirror || null != foActual;
  int sleepInterval=100;
  long timeoutUnitInterval;
  boolean useFailoverHost=false;
  FailoverInfo tempFailover=null;
  ServerPortPlaceHolder currentFOPlaceHolder=null;
  ServerPortPlaceHolder currentPrimaryPlaceHolder=null;
  if (null != foActual) {
    tempFailover=foActual;
    useFailoverHost=foActual.getUseFailoverPartner();
  }
 else {
    if (isDBMirroring) {
      tempFailover=new FailoverInfo(mirror,this,false);
    }
  }
  boolean useParallel=getMultiSubnetFailover();
  boolean useTnir=getTransparentNetworkIPResolution();
  long intervalExpire;
  if (0 == timeout) {
    timeout=SQLServerDriverIntProperty.LOGIN_TIMEOUT.getDefaultValue();
  }
  long timerTimeout=timeout * 1000L;
  timerExpire=timerStart + timerTimeout;
  if (isDBMirroring || useParallel) {
    timeoutUnitInterval=(long)(TIMEOUTSTEP * timerTimeout);
  }
 else   if (useTnir) {
    timeoutUnitInterval=(long)(TIMEOUTSTEP_TNIR * timerTimeout);
  }
 else {
    timeoutUnitInterval=timerTimeout;
  }
  intervalExpire=timerStart + timeoutUnitInterval;
  long intervalExpireFullTimeout=timerStart + timerTimeout;
  if (connectionlogger.isLoggable(Level.FINER)) {
    connectionlogger.finer(toString() + " Start time: " + timerStart + " Time out time: " + timerExpire + " Timeout Unit Interval: " + timeoutUnitInterval);
  }
  int attemptNumber=0;
  int noOfRedirections=0;
  while (true) {
    clientConnectionId=null;
    state=State.Initialized;
    try {
      if (isDBMirroring && useFailoverHost) {
        if (null == currentFOPlaceHolder) {
          currentFOPlaceHolder=tempFailover.failoverPermissionCheck(this,integratedSecurity);
        }
        currentConnectPlaceHolder=currentFOPlaceHolder;
      }
 else {
        if (routingInfo != null) {
          currentPrimaryPlaceHolder=routingInfo;
          routingInfo=null;
        }
 else         if (null == currentPrimaryPlaceHolder) {
          currentPrimaryPlaceHolder=primaryPermissionCheck(primary,primaryInstanceName,primaryPortNumber);
        }
        currentConnectPlaceHolder=currentPrimaryPlaceHolder;
      }
      if (connectionlogger.isLoggable(Level.FINE)) {
        connectionlogger.fine(toString() + " This attempt server name: " + currentConnectPlaceHolder.getServerName() + " port: " + currentConnectPlaceHolder.getPortNumber() + " InstanceName: " + currentConnectPlaceHolder.getInstanceName() + " useParallel: " + useParallel);
        connectionlogger.fine(toString() + " This attempt endtime: " + intervalExpire);
        connectionlogger.fine(toString() + " This attempt No: " + attemptNumber);
      }
      connectHelper(currentConnectPlaceHolder,timerRemaining(intervalExpire),timeout,useParallel,useTnir,(0 == attemptNumber),timerRemaining(intervalExpireFullTimeout));
      if (isRoutedInCurrentAttempt) {
        if (isDBMirroring) {
          String msg=SQLServerException.getErrString("R_invalidRoutingInfo");
          terminate(SQLServerException.DRIVER_ERROR_UNSUPPORTED_CONFIG,msg);
        }
        noOfRedirections++;
        if (noOfRedirections > 1) {
          String msg=SQLServerException.getErrString("R_multipleRedirections");
          terminate(SQLServerException.DRIVER_ERROR_UNSUPPORTED_CONFIG,msg);
        }
        if (tdsChannel != null)         tdsChannel.close();
        initResettableValues();
        resetNonRoutingEnvchangeValues();
        attemptNumber++;
        isRoutedInCurrentAttempt=false;
        useParallel=false;
        useTnir=false;
        intervalExpire=timerExpire;
        if (timerHasExpired(timerExpire)) {
          MessageFormat form=new MessageFormat(SQLServerException.getErrString("R_tcpipConnectionFailed"));
          Object[] msgArgs={currentConnectPlaceHolder.getServerName(),Integer.toString(currentConnectPlaceHolder.getPortNumber()),SQLServerException.getErrString("R_timedOutBeforeRouting")};
          String msg=form.format(msgArgs);
          terminate(SQLServerException.DRIVER_ERROR_UNSUPPORTED_CONFIG,msg);
        }
 else {
          continue;
        }
      }
 else       break;
    }
 catch (    SQLServerException sqlex) {
      if ((SQLServerException.LOGON_FAILED == sqlex.getErrorCode()) || (SQLServerException.PASSWORD_EXPIRED == sqlex.getErrorCode()) || (SQLServerException.USER_ACCOUNT_LOCKED == sqlex.getErrorCode()) || (SQLServerException.DRIVER_ERROR_INVALID_TDS == sqlex.getDriverErrorCode()) || (SQLServerException.DRIVER_ERROR_SSL_FAILED == sqlex.getDriverErrorCode()) || (SQLServerException.DRIVER_ERROR_INTERMITTENT_TLS_FAILED == sqlex.getDriverErrorCode()) || (SQLServerException.DRIVER_ERROR_UNSUPPORTED_CONFIG == sqlex.getDriverErrorCode()) || (SQLServerException.ERROR_SOCKET_TIMEOUT == sqlex.getDriverErrorCode()) || timerHasExpired(timerExpire) || (state.equals(State.Connected) && !isDBMirroring)) {
        close();
        throw sqlex;
      }
 else {
        if (null != tdsChannel)         tdsChannel.close();
      }
      if (!isDBMirroring || 1 == attemptNumber % 2) {
        long remainingMilliseconds=timerRemaining(timerExpire);
        if (remainingMilliseconds <= sleepInterval) {
          throw sqlex;
        }
      }
    }
    if (!isDBMirroring || (1 == attemptNumber % 2)) {
      if (connectionlogger.isLoggable(Level.FINE)) {
        connectionlogger.fine(toString() + " sleeping milisec: " + sleepInterval);
      }
      try {
        Thread.sleep(sleepInterval);
      }
 catch (      InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      sleepInterval=(sleepInterval < 500) ? sleepInterval * 2 : 1000;
    }
    attemptNumber++;
    if (useParallel) {
      intervalExpire=System.currentTimeMillis() + (timeoutUnitInterval * (attemptNumber + 1));
    }
 else     if (isDBMirroring) {
      intervalExpire=System.currentTimeMillis() + (timeoutUnitInterval * ((attemptNumber / 2) + 1));
    }
 else     if (useTnir) {
      long timeSlice=timeoutUnitInterval * (1 << attemptNumber);
      if ((1 == attemptNumber) && (500 > timeSlice)) {
        timeSlice=500;
      }
      intervalExpire=System.currentTimeMillis() + timeSlice;
    }
 else     intervalExpire=timerExpire;
    if (intervalExpire > timerExpire) {
      intervalExpire=timerExpire;
    }
    if (isDBMirroring) {
      useFailoverHost=!useFailoverHost;
    }
  }
  if (useFailoverHost && null == failoverPartnerServerProvided) {
    String curserverinfo=currentConnectPlaceHolder.getServerName();
    if (null != currentFOPlaceHolder.getInstanceName()) {
      curserverinfo=curserverinfo + "\\";
      curserverinfo=curserverinfo + currentFOPlaceHolder.getInstanceName();
    }
    MessageFormat form=new MessageFormat(SQLServerException.getErrString("R_invalidPartnerConfiguration"));
    Object[] msgArgs={activeConnectionProperties.getProperty(SQLServerDriverStringProperty.DATABASE_NAME.toString()),curserverinfo};
    terminate(SQLServerException.DRIVER_ERROR_UNSUPPORTED_CONFIG,form.format(msgArgs));
  }
  if (null != failoverPartnerServerProvided) {
    if (multiSubnetFailover) {
      String msg=SQLServerException.getErrString("R_dbMirroringWithMultiSubnetFailover");
      terminate(SQLServerException.DRIVER_ERROR_UNSUPPORTED_CONFIG,msg);
    }
    if ((applicationIntent != null) && applicationIntent.equals(ApplicationIntent.READ_ONLY)) {
      String msg=SQLServerException.getErrString("R_dbMirroringWithReadOnlyIntent");
      terminate(SQLServerException.DRIVER_ERROR_UNSUPPORTED_CONFIG,msg);
    }
    if (null == tempFailover)     tempFailover=new FailoverInfo(failoverPartnerServerProvided,this,false);
    if (null != foActual) {
      foActual.failoverAdd(this,useFailoverHost,failoverPartnerServerProvided);
    }
 else {
      String databaseNameProperty=SQLServerDriverStringProperty.DATABASE_NAME.toString();
      String instanceNameProperty=SQLServerDriverStringProperty.INSTANCE_NAME.toString();
      String serverNameProperty=SQLServerDriverStringProperty.SERVER_NAME.toString();
      if (connectionlogger.isLoggable(Level.FINE)) {
        connectionlogger.fine(toString() + " adding new failover info server: " + activeConnectionProperties.getProperty(serverNameProperty) + " instance: " + activeConnectionProperties.getProperty(instanceNameProperty) + " database: " + activeConnectionProperties.getProperty(databaseNameProperty) + " server provided failover: " + failoverPartnerServerProvided);
      }
      tempFailover.failoverAdd(this,useFailoverHost,failoverPartnerServerProvided);
      FailoverMapSingleton.putFailoverInfo(this,primary,activeConnectionProperties.getProperty(instanceNameProperty),activeConnectionProperties.getProperty(databaseNameProperty),tempFailover,useFailoverHost,failoverPartnerServerProvided);
    }
  }
}
