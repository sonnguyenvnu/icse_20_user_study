private void upgrade(JcrAllowedActions allowed,Set<String> userNames,Set<String> groupNames){
  allowed.streamActions().forEach(action -> {
    allowed.getPrincipalsAllowedAll(action).stream().filter(this::isUpgradable).forEach(principal -> {
      if (groupNames.contains(principal.getName())) {
        GroupPrincipal group=new GroupPrincipal(principal.getName());
        allowed.enable(group,action);
      }
 else       if (userNames.contains(principal.getName())) {
        UsernamePrincipal newPrincipal=new UsernamePrincipal(principal.getName());
        allowed.enable(newPrincipal,action);
      }
    }
);
  }
);
  allowed.streamActions().forEach(action -> {
    allowed.getPrincipalsAllowedAll(action).stream().filter(this::isUpgradable).forEach(principal -> {
      if (!(principal instanceof UsernamePrincipal || principal instanceof Group)) {
        allowed.disable(new RemovedPrincipal(principal),action);
      }
    }
);
  }
);
}
