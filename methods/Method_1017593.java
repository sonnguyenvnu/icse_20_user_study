@PostConstruct public void setup(){
  stateMachine.addStateListener(new StateMachineListenerAdapter<States,Events>(){
    @Override public void stateEntered(    State<States,Events> state){
      StateMachineMessage message=new StateMachineMessage();
      message.setMessage("Enter state " + state.getId().toString());
      simpMessagingTemplate.convertAndSend("/topic/sm.message",message);
    }
    @Override public void stateExited(    State<States,Events> state){
      StateMachineMessage message=new StateMachineMessage();
      message.setMessage("Exit state " + state.getId().toString());
      simpMessagingTemplate.convertAndSend("/topic/sm.message",message);
    }
    @Override public void stateChanged(    State<States,Events> from,    State<States,Events> to){
      Map<Object,Object> variables=stateMachine.getExtendedState().getVariables();
      ArrayList<StateMachineEvent> list=new ArrayList<StateMachineEvent>();
      for (      States state : stateMachine.getState().getIds()) {
        list.add(new StateMachineEvent(state.toString()));
      }
      simpMessagingTemplate.convertAndSend("/topic/sm.states",list);
      simpMessagingTemplate.convertAndSend("/topic/sm.variables",variables);
    }
    @Override public void transitionEnded(    Transition<States,Events> transition){
      if (transition != null && transition.getKind() == TransitionKind.INTERNAL) {
        Map<Object,Object> variables=stateMachine.getExtendedState().getVariables();
        simpMessagingTemplate.convertAndSend("/topic/sm.variables",variables);
      }
    }
    @Override public void stateMachineError(    StateMachine<States,Events> stateMachine,    Exception exception){
      handleStateMachineError(new StateMachineException("Received error from machine",exception));
    }
  }
);
  stateMachineEnsemble.addEnsembleListener(new EnsembleListenerAdapter<States,Events>(){
    @Override public void ensembleLeaderGranted(    StateMachine<States,Events> stateMachine){
      StateMachineMessage message=new StateMachineMessage();
      message.setMessage("Leader granted " + stateMachine.getUuid().toString());
      simpMessagingTemplate.convertAndSend("/topic/sm.message",message);
    }
    @Override public void ensembleLeaderRevoked(    StateMachine<States,Events> stateMachine){
      StateMachineMessage message=new StateMachineMessage();
      message.setMessage("Leader revoked " + stateMachine.getUuid().toString());
      simpMessagingTemplate.convertAndSend("/topic/sm.message",message);
    }
  }
);
}
