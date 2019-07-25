/*
 * Copyright 2003-2018 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jetbrains.mps.generator.trace;

import jetbrains.mps.components.CoreComponent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Entry component for generator trace mechanism.
 *
 * NOT THREAD-SAFE AT THE MOMENT
 *
 * @author Artem Tikhomirov
 * @since 2017.3
 */
public class TraceRegistry implements CoreComponent {
  // we hold instances of objects we have full control of
  // as TraceClient instance could come and go (e.g. reloaded).
  private final List<ClientToken> myActiveClients;
  private final List<ClientToken> myInactiveClients;

  public TraceRegistry() {
    myActiveClients = new ArrayList<>(2);
    myInactiveClients = new ArrayList<>(2);
  }

  public ClientToken subscribe(@NotNull TraceClient client) {
    ClientToken rv = new ClientToken();
    // perform handshake with the client to ensure its capabilities
    myActiveClients.add(rv);
    return rv;
  }

  public void unsubscribe(@NotNull ClientToken token) {
    if (myActiveClients.remove(token)) {
      token.deactivate();
      myInactiveClients.add(token);
    }
  }

  public TraceFacility createSession() {
    // on one hand, I can pass TraceClient/ClientToken right here in this method, OTOH, the idea here is to keep client registration
    // separate from the session/trace activation, so that TraceClients may behave as "global" listeners for any transformation session
    // activated by anyone (provided this m2m session opts to invoke this method).
    //
    // perhaps, it's the right moment to consult tokens if they are still alive or interested to take part (we can pass some identification
    // here to tell one m2m scenario from another)
    return new TraceFacility(new ArrayList<>(myActiveClients));
  }

  @Override
  public void init() {
  }

  @Override
  public void dispose() {
  }
}
