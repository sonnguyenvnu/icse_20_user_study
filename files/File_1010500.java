/*
 * Copyright 2003-2014 JetBrains s.r.o.
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
package jetbrains.mps.smodel.persistence.def.v9;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SModelReference;

import java.util.HashMap;
import java.util.Set;

/**
 * Keeps information about model imports. Both write and read code use this to track
 * model imports and their respective index values.
 *
 * The way model imports as well as node references utilizing these imports get serialized
 * is external to this class, see {@link jetbrains.mps.smodel.persistence.def.v9.IdEncoder#toTextExternal(ImportsHelper, org.jetbrains.mps.openapi.model.SReference)}
` *
 * Alternative to StorageIndexHelper9, index generation code (although questionable) is identical.
 */
class ImportsHelper {
  private final SModelReference myModelRef;
  private final HashMap<SModelReference, String> myModel2Index = new HashMap<>();
  private final HashMap<String, SModelReference> myIndex2Model = new HashMap<>();

  private static final int HASH_BASE = 10 + 26;
  private static final int HASH_SIZE = HASH_BASE * HASH_BASE * HASH_BASE * HASH_BASE;

  public ImportsHelper(@NotNull SModelReference model) {
    myModelRef = model;
  }

  public String addModelImport(@NotNull SModelReference modelReference) {
    if (myModel2Index.containsKey(modelReference)) {
//      assert !myModel2Index.containsKey(modelReference);
      Logger.getLogger(ImportsHelper.class).error(String.format("Model %s has duplicating import %s, ignored", myModelRef, modelReference));
      return myModel2Index.get(modelReference);
    }
    String rv = createIndexFor(modelReference.getModelId().hashCode(), myIndex2Model.keySet());
    register(rv, modelReference);
    return rv;
  }

  public void addModelImport(@NotNull String index, @NotNull SModelReference modelReference) {
    if (myIndex2Model.containsKey(index)) {
//      throw new IllegalArgumentException(String.format...);
      Logger.getLogger(ImportsHelper.class).error(String.format("Can't register import %s in model %s. Index %s is already in use for model %s", modelReference, myModelRef, index, myIndex2Model.get(index)));
      return;
    }
    register(index, modelReference);
  }

  public String getIndex(@NotNull SModelReference modelReference) {
    assert myModel2Index.containsKey(modelReference) : String.valueOf(modelReference);
    return myModel2Index.get(modelReference);
  }

  public SModelReference getModelReference(@NotNull String index) {
    assert myIndex2Model.containsKey(index);
    return myIndex2Model.get(index);
  }

  // algorithm copied from StorageIndexHelper9.addInternalObject
  /**
   * Return value shall not use symbols other than [0-9][a-z] as the index is serialized as part of node identification,
   * with {@link jetbrains.mps.smodel.persistence.def.v9.IdEncoder#REF_TARGET_IMPORT_SEPARATOR} as separator
   */
  private String createIndexFor(int initialHash, Set<String> usedIndex) {
    int hash = (initialHash % HASH_SIZE + HASH_SIZE) % HASH_SIZE;
    String rv;
    do {
      rv = Integer.toString(hash, HASH_BASE);
      hash = (hash + 1) % HASH_SIZE;
    } while (usedIndex.contains(rv));
    return rv;
  }

  private void register(String index, SModelReference modelReference) {
    if (myModelRef.equals(modelReference)) {
//      assert !myModelRef.equals(modelReference) : String.format("Model %s: no reason to keep imports to self", myModelRef);
      Logger.getLogger(ImportsHelper.class).warn(String.format("Model %s: no reason to keep imports to self", myModelRef));
    }
    myIndex2Model.put(index, modelReference);
    myModel2Index.put(modelReference, index);
  }

  public boolean isLocal(SModelReference targetModel) {
    return targetModel.equals(myModelRef);
  }

  public SModelReference localModel() {
    return myModelRef;
  }
}
