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

import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SAbstractConcept;

/**
 * Translation from {@link org.jetbrains.mps.openapi.language.SAbstractConcept SConcept} to integer value.
 * @author Artem Tikhomirov
 * @since 3.5
 */
public interface ConceptIndex {
  /**
   * @param c concept to look up
   * @return -1 if this index is not aware of the concept, or concept is null
   */
  default int index(@Nullable SAbstractConcept c) {
    return index(c, -1);
  }

  /**
   * @param c concept to look up
   * @param missingValue value indicating index miss
   * @return integer index of the concept, or {@code missingValue} if concept is null or not known to the index.
   */
  int index(@Nullable SAbstractConcept c, int missingValue);
}
