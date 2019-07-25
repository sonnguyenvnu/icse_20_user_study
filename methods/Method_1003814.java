@Override public void handle(Context ctx) throws Exception {
  PathBinding pathBinding=ctx.getPathBinding();
  String pastBinding=pathBinding.getPastBinding();
  if (pastBinding.equals(path)) {
    RatpackWebContext.from(ctx,true).flatMap(webContext -> {
      SessionData sessionData=webContext.getSession();
      return createClients(ctx,pathBinding).map(clients -> clients.findClient(webContext)).map(Types::<Client<Credentials,UserProfile>>cast).flatMap(client -> getProfile(webContext,client)).map(profile -> {
        if (profile != null) {
          sessionData.set(Pac4jSessionKeys.USER_PROFILE,profile);
        }
        Optional<String> originalUrl=sessionData.get(Pac4jSessionKeys.REQUESTED_URL);
        sessionData.remove(Pac4jSessionKeys.REQUESTED_URL);
        return originalUrl;
      }
).onError(t -> {
        if (t instanceof RequiresHttpAction) {
          webContext.sendResponse((RequiresHttpAction)t);
        }
 else {
          ctx.error(new TechnicalException("Failed to get user profile",t));
        }
      }
);
    }
).then(originalUrlOption -> {
      ctx.redirect(originalUrlOption.orElse("/"));
    }
);
  }
 else {
    createClients(ctx,pathBinding).then(clients -> {
      Registry registry=Registry.singleLazy(Clients.class,() -> uncheck(() -> clients));
      ctx.next(registry);
    }
);
  }
}
