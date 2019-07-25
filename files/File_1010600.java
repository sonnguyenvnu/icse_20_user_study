/*
 * Copyright 2003-2019 JetBrains s.r.o.
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
package jetbrains.mps.smodel.references;

import gnu.trove.THashSet;
import gnu.trove.TObjectIdentityHashingStrategy;
import jetbrains.mps.smodel.SReferenceBase;
import jetbrains.mps.smodel.StaticReference;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.module.SModule;

import java.util.Set;


// Given the fact it's MA with its command start/finish events to turn this ImmatureReferences collector on/off,
// and there's only 1 command thread, we resort to thread-local non-synchronized collection of references that need to be
// processed once command is over. Besides, as we invoke instance methods on these references, we can use 'identity' to match them (no need to bother with
// hashCode/equals).
// With present approach, nodes created in threads other than command one won't get their references recorded here. Though it's slightly different from the
// earlier approach (where any thread producing a node b/w enable/disable got it recorded), it's still valid, as there never was a guarantee than the parallel
// thread producing nodes would get exactly b/w enable/disable calls. If, however, there's explicit synchronization b/w threads to accomplish that, I'd better
// figure it out. This mechanism is internal implementation detail, and there should be no code to rely/utilize it
//
// This is not a CoreComponent, rather an implementation friend of MA class. Since we don not control StaticReference instantiation, we resort to this
// singleton class to record model reference come and go.
public final class ImmatureReferences {

  private static ImmatureReferences INSTANCE = new ImmatureReferences();

  // FIXME shall retrieve instance per SRepository
  public static ImmatureReferences getInstance() {
    return INSTANCE;
  }

  private final ThreadLocal<Set<StaticReference>> myReferences = new ThreadLocal<>();
  private final TObjectIdentityHashingStrategy<StaticReference> myHashStrategy = new TObjectIdentityHashingStrategy<>();

  private boolean myDisabled = true;

  private ImmatureReferences() {
  }

  public void enable() {
    myDisabled = false;
    Set<StaticReference> existing = myReferences.get();
    if (existing == null) {
      existing = new THashSet<>(myHashStrategy);
      myReferences.set(existing);
    } else {
      existing.clear();
    }
  }

  public void disable() {
    myDisabled = true;
    cleanup();
  }

  public void cleanup() {
    final Set<StaticReference> existing = myReferences.get();
    if (existing == null || existing.isEmpty()) {
      // odd, why would anyone call cleanup from a thread that didn't enable collection?
      return;
    }
    final StaticReference[] copy = existing.toArray(new StaticReference[existing.size()]);
    // I don't bother with set(null) intentionally as I expect this thread to live on and the set to be reused
    existing.clear(); // clear right away, don't waste time in remove()
    // makeIndirect might end up with remove() and modification of the set
    for (StaticReference r : copy) {
      r.makeIndirect(true);
    }
  }

  /**
   * @param ref non-null
   */
  public void add(StaticReference ref) {
    if (myDisabled) {
      return;
    }
    final Set<StaticReference> existing = myReferences.get();
    if (existing == null) {
      // XXX log, perhaps? A node/reference created during command but from a thread that didn't initiate it.
      return;
    }
    existing.add(ref);
  }

  // XXX Here used to be repository listener mechanism to find out about model removal, while for the rest of functionality
  //     relies on explicit IR.getInstance() calls. Therefore, changed to yet another explicit call.
  public void beforeModelRemoved(SModule module, SModel model) {
    if (myDisabled) {
      return;
    }
    final Set<StaticReference> existing = myReferences.get();
    if (existing == null || existing.isEmpty()) {
      return;
    }
    final SModelReference toRemove = model.getReference();
    // Due to nice design, SR.getTargetSModelReference() may lead to change in 'existing' set (SR.makeIndirect->IR.remove),
    // therefore, can't iterate over existing and at the same time ask for getTargetSModelReference.
    // FIXME If I don't get rid of this code any time soon, perhaps, shall try to make getTargetSModelReference() side-effect free,
    // i.e. to rely on external code (e.g. commandFinished()) to fix references as appropriate, and use whatever is available
    // the moment getTargetSModelReference() is invoked (e.g. myImmatureTargetNode.getModel().getReference instead of makeIndirect)
    THashSet<StaticReference> matching = new THashSet<>(existing, myHashStrategy);
    matching.removeIf(sr -> !toRemove.equals(sr.getTargetSModelReference()));
    myReferences.get().removeAll(matching);
  }

  /**
   * @param ref non-null (not that we use that, but earlier code did assume that)
   */
  public void remove(SReferenceBase ref) {
    if (myDisabled) {
      return;
    }

    final Set<StaticReference> existing = myReferences.get();
    if (existing == null) {
      // XXX log, perhaps? A node/reference created during command but from a thread that didn't initiate it.
      return;
    }
    existing.remove(ref);
  }
}
