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

/**
 * Facility to build index of concepts from the same language
 * @author Artem Tikhomirov
 */
public final class LanguageConceptIndexBuilder {
  private final TLongIntHashMap myIndex = new TLongIntHashMap();
  // XXX pass these into LCI and assert/check concepts coming into its index() methods are from the language
  private final long myLangHighBits;
  private final long myLangLowBits;
  // prevent from modification of exposed map instance (don't want to copy map on seal())
  private boolean myIsSealed = false;

  public LanguageConceptIndexBuilder() {
    myLangHighBits = myLangLowBits = -1;
  }

  public LanguageConceptIndexBuilder(long langHighBits, long langLowBits) {
    myLangHighBits = langHighBits;
    myLangLowBits = langLowBits;
  }

  public LanguageConceptIndexBuilder put(long conceptId, int index) {
    assert !myIsSealed;
    myIndex.put(conceptId, index);
    return this;
  }

  public LanguageConceptIndex seal() {
    assert !myIsSealed;
    myIsSealed = true;
    myIndex.compact();
    return new LanguageConceptIndex(myIndex);
  }
}
