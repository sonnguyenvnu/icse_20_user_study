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
package jetbrains.mps.generator.impl;

import jetbrains.mps.util.containers.ConcurrentHashSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelReference;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Creates, clones, holds and tracks models instantiated during generation session.
 * Unlike TransientModelsModule, has no responsibility whatsoever about exposing/publishing these models
 *
 * @author Artem Tikhomirov
 */
public class ModelVault<T extends SModel> {
  private Set<SModelReference> myModelsToPublish = new ConcurrentHashSet<>();
  private Set<T> myModels = new ConcurrentHashSet<>();
  private Map<SModel,Boolean> myExactModelsToDrop = new IdentityHashMap<>();

  public void add(@NotNull T model) {
    myModels.add(model);
  }

  public void remove(@NotNull SModel model) {
    myModels.remove(model);
    // add/remove deal with myModels only, while publish/forget pair deals with myModelsToPublish.
    // if there's need to forget model to publish along with removal from the vault, use explicit forget()
//    myModelsToPublish.remove(model.getReference());
  }

  public void publish(SModelReference modelReference) {
    myModelsToPublish.add(modelReference);
  }

  public void forget(SModel model) {
    myModelsToPublish.remove(model.getReference());
    myExactModelsToDrop.put(model, Boolean.TRUE);
  }

  public boolean isPublished(SModelReference modelReference) {
    return myModelsToPublish.contains(modelReference);
  }

  public Iterable<T> modelsToPublish() {
    HashSet<T> rv = new HashSet<>(myModels);
    HashSet<SModelReference> collected = new HashSet<>();
    for (Iterator<T> it = rv.iterator(); it.hasNext();) {
      T next = it.next();
      final SModelReference nextRef = next.getReference();
      if (!isPublished(nextRef) || myExactModelsToDrop.containsKey(next)) {
        it.remove();
        continue;
      }
      if (collected.contains(nextRef)) {
        throw new IllegalStateException(String.format("There's more than one instance of model identified with reference %s, can't decide which one to publish", nextRef));
      }
      collected.add(nextRef);
    }
    return rv;
  }

  public Iterable<T> modelsNotToPublish() {
    HashSet<T> rv = new HashSet<>(myModels);
    for (Iterator<T> it = rv.iterator(); it.hasNext();) {
      T next = it.next();
      if (isPublished(next.getReference()) && !myExactModelsToDrop.containsKey(next)) {
        it.remove();
      }
    }
    return rv;
  }

  public Iterable<T> allModels() {
    return new ArrayList<>(myModels);
  }

  // XXX not sure there's need for distinct method or it shall be part of allModels() impl,
  //     nor am I sure we shall not resolve references into models to drop (I'd rather do).
  //     However, imagine there's already model m1@cp in a checkpoint module, and we generate m1 and m2 (latter needs to use m1@cp)
  //     When we see m1's new checkpoint, CME.createBlankCheckpointModel recognizes there's already m1@cp and commands to forget it (vault.forgetModel())
  //     Then, m2 restores a reference to m1@cp with a node from new CP model (m1'@cp), but the reference is kept as 'mature', and the moment we resolve it,
  //     the code in TransientModelsModule.findInVault() just took the first model from allModels() with matching id, which could be the m1@cp one, not m1'@cp
  //     (they share same module reference, after all).
  public Iterable<T> allModelsExceptScheduled2Drop() {
    final ArrayList<T> rv = new ArrayList<>(myModels);
    rv.removeAll(myExactModelsToDrop.keySet());
    return rv;
  }

  public boolean isScheduled2Drop(SModel model) {
    return myExactModelsToDrop.containsKey(model);
  }


  public boolean known(@NotNull SModelReference mr) {
    for (SModel m : myModels) {
      if (mr.equals(m.getReference())) {
        return true;
      }
    }
    return false;
  }

  public boolean known(@NotNull SModel model) {
    return myModels.contains(model);
  }

  public void clear() {
    myModelsToPublish.clear();
    myModels.clear();
  }
}
