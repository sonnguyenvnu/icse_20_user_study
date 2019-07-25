/*
 * Copyright 2003-2016 JetBrains s.r.o.
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
package jetbrains.mps.lang.smodel;

import gnu.trove.TLongIntHashMap;
import jetbrains.mps.smodel.adapter.ids.MetaIdHelper;
import jetbrains.mps.smodel.adapter.ids.SConceptId;
import org.jetbrains.mps.openapi.language.SAbstractConcept;

/**
 * Index for concepts from the same language.
 * @see LanguageConceptIndexBuilder
 * @author Artem Tikhomirov
 * @since 3.5
 */
public final class LanguageConceptIndex implements ConceptIndex {
  private final TLongIntHashMap myIndex;

  /*package*/ LanguageConceptIndex(TLongIntHashMap index) {
    myIndex = index;
  }

  /**
   * Internal API, intended for use from StructureAspectDescriptor.
   */
  public int index(SConceptId cid) {
    long key = cid.getIdValue();
    return myIndex.containsKey(key) ? myIndex.get(key) : -1;
  }

  @Override
  public int index(SAbstractConcept c, int missingValue) {
    if (c == null) {
      return missingValue;
    }
    long key = MetaIdHelper.getConcept(c).getIdValue();
    if (myIndex.containsKey(key)) {
      return myIndex.get(key);
    }
    return missingValue;

  }
}
