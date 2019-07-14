/** 
 * Fires the Madvoc event. Warning: since event handlers may register more handlers, we must collect first the list of components that matches the type and then to execute.
 */
public void fireEvent(final Class listenerType){
  final Set<String> existing=new HashSet<>();
  while (true) {
    MutableInteger newCount=MutableInteger.of(0);
    madpc.forEachBeanType(listenerType,name -> {
      if (existing.add(name)) {
        newCount.value++;
        Object listener=lookupComponent(name);
        if (listener != null) {
          MadvocComponentLifecycle.invoke(listener,listenerType);
        }
      }
    }
);
    if (newCount.value == 0) {
      break;
    }
  }
}
