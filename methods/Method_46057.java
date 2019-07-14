@Override public void onEvent(Event originEvent){
  Class eventClass=originEvent.getClass();
  if (eventClass == ClientSyncReceiveEvent.class) {
    if (!FaultToleranceConfigManager.isEnable()) {
      return;
    }
    ClientSyncReceiveEvent event=(ClientSyncReceiveEvent)originEvent;
    ConsumerConfig consumerConfig=event.getConsumerConfig();
    ProviderInfo providerInfo=event.getProviderInfo();
    InvocationStat result=InvocationStatFactory.getInvocationStat(consumerConfig,providerInfo);
    if (result != null) {
      result.invoke();
      Throwable t=event.getThrowable();
      if (t != null) {
        result.catchException(t);
      }
    }
  }
 else   if (eventClass == ClientAsyncReceiveEvent.class) {
    if (!FaultToleranceConfigManager.isEnable()) {
      return;
    }
    ClientAsyncReceiveEvent event=(ClientAsyncReceiveEvent)originEvent;
    ConsumerConfig consumerConfig=event.getConsumerConfig();
    ProviderInfo providerInfo=event.getProviderInfo();
    InvocationStat result=InvocationStatFactory.getInvocationStat(consumerConfig,providerInfo);
    if (result != null) {
      result.invoke();
      Throwable t=event.getThrowable();
      if (t != null) {
        result.catchException(t);
      }
    }
  }
 else   if (eventClass == ProviderInfoRemoveEvent.class) {
    ProviderInfoRemoveEvent event=(ProviderInfoRemoveEvent)originEvent;
    ConsumerConfig consumerConfig=event.getConsumerConfig();
    ProviderGroup providerGroup=event.getProviderGroup();
    if (!ProviderHelper.isEmpty(providerGroup)) {
      for (      ProviderInfo providerInfo : providerGroup.getProviderInfos()) {
        InvocationStatFactory.removeInvocationStat(consumerConfig,providerInfo);
      }
    }
  }
 else   if (eventClass == ProviderInfoUpdateEvent.class) {
    ProviderInfoUpdateEvent event=(ProviderInfoUpdateEvent)originEvent;
    ConsumerConfig consumerConfig=event.getConsumerConfig();
    List<ProviderInfo> add=new ArrayList<ProviderInfo>();
    List<ProviderInfo> remove=new ArrayList<ProviderInfo>();
    ProviderHelper.compareGroup(event.getOldProviderGroup(),event.getNewProviderGroup(),add,remove);
    for (    ProviderInfo providerInfo : remove) {
      InvocationStatFactory.removeInvocationStat(consumerConfig,providerInfo);
    }
  }
 else   if (eventClass == ProviderInfoUpdateAllEvent.class) {
    ProviderInfoUpdateAllEvent event=(ProviderInfoUpdateAllEvent)originEvent;
    ConsumerConfig consumerConfig=event.getConsumerConfig();
    List<ProviderInfo> add=new ArrayList<ProviderInfo>();
    List<ProviderInfo> remove=new ArrayList<ProviderInfo>();
    ProviderHelper.compareGroups(event.getOldProviderGroups(),event.getNewProviderGroups(),add,remove);
    for (    ProviderInfo providerInfo : remove) {
      InvocationStatFactory.removeInvocationStat(consumerConfig,providerInfo);
    }
  }
}
