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
package jetbrains.mps.util;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
* User: fyodor
* Date: 8/27/12
*/
public class SimpleLRUCache<K> {

  private static final int DEFAULT_MAX_SIZE = 20000;
  private static final double FIRST_LEVEL_RATIO = 0.6;
  private static final double CLEANUP_Q1_RATIO = 0.06;

  private final AtomicInteger roomLeftFirstLevel;
  private final AtomicInteger roomLeftSecondLevel;

  private final ConcurrentHashMap<K, K> firstLevelCache; // aka L1
  private final ConcurrentLinkedQueue<K> firstLevelQueue = new ConcurrentLinkedQueue<>(); // aka Q1

  private final ConcurrentHashMap<K, K> secondLevelCache; // aka L2
  private final ConcurrentLinkedQueue<K> secondLevelQueue = new ConcurrentLinkedQueue<>(); // aka Q2

  private final ConcurrentHashMap<K, K> transitionalCache = new ConcurrentHashMap<>();

  private int promotesBeforeCleanupInitialValue;
  private final AtomicInteger promotesBeforeCleanup;

  public SimpleLRUCache(int maxSize) {
    final int sizeL1 = (int) (maxSize * FIRST_LEVEL_RATIO);
    final int sizeL2 = (int) (maxSize * (1. - FIRST_LEVEL_RATIO));
    roomLeftFirstLevel = new AtomicInteger(sizeL1);
    roomLeftSecondLevel = new AtomicInteger(sizeL2);
    // compensate HashMap size for default load factor of 0.75
    firstLevelCache = new ConcurrentHashMap<>(sizeL1 * 4 / 3);
    secondLevelCache = new ConcurrentHashMap<>(sizeL2 * 4 / 3);
    promotesBeforeCleanupInitialValue = (int) (maxSize * CLEANUP_Q1_RATIO);
    promotesBeforeCleanup = new AtomicInteger(promotesBeforeCleanupInitialValue);
  }

  public SimpleLRUCache() {
    this(DEFAULT_MAX_SIZE);
  }

  public int size() {
    return firstLevelCache.size() + secondLevelCache.size();
  }

  protected K canonic(K k) {
    return k;
  }

  protected void purged(K k) {}

  @Override
  public String toString() {
    return "LRU["+firstLevelCache.size()+", "+secondLevelCache.size()+"]";
  }

  public K cacheObject (K toCache) {
    K cached = secondLevelCache.get(toCache);
    if (cached != null) {
      return cached;
    }

    cached = firstLevelCache.get(toCache);
    if (cached != null) {
      return primPromote(cached);
    }

    cached = transitionalCache.get(toCache);
    if (cached != null) {
      return cached;
    }

    return primCacheObject(canonic(toCache));
  }

  private K primPromote(K cached) {
    if (lock(cached)) {
      // current thread has a mutex on 'cached'

      K alreadyPromoted = secondLevelCache.putIfAbsent(cached, cached);
      if (alreadyPromoted != null) {
        unlock(cached);
        return alreadyPromoted;
      }
      secondLevelQueue.add(cached);

      if (firstLevelCache.remove(cached, cached)) {
        roomLeftFirstLevel.incrementAndGet();
        if (promotesBeforeCleanup.decrementAndGet() <= 0) {
          cleanupQ1();
        }
      }

      if (roomLeftSecondLevel.decrementAndGet() <= 0) {
        K toDemote = secondLevelQueue.poll();
        assert toDemote != null;

        primCacheObject(toDemote);

        if (lock(toDemote)) {
          if (secondLevelCache.remove(toDemote, toDemote)) {
            roomLeftSecondLevel.incrementAndGet();
          }
          unlock(toDemote);
        } else {
          secondLevelQueue.add(toDemote);
        }
      }

      unlock(cached);
    }
    return cached;
  }

  private K primCacheObject(K canonic) {
    if (lock(canonic)) {
      // current thread has a mutex on 'canonic'

      K alreadyCached = firstLevelCache.putIfAbsent(canonic, canonic);

      if (alreadyCached != null) {
        unlock(canonic);
        return alreadyCached;
      }

      firstLevelQueue.add(canonic);

      if (roomLeftFirstLevel.decrementAndGet() <= 0) {
        K toRemove;
        do {
          toRemove = firstLevelQueue.poll();
          assert toRemove != null;
        } while (!firstLevelCache.containsKey(toRemove));

        if (lock(toRemove)) {
          if (firstLevelCache.remove(toRemove, toRemove)) {
            roomLeftFirstLevel.incrementAndGet();
            purged(toRemove);
          }
          unlock(toRemove);
        } else {
          firstLevelQueue.add(toRemove);
        }
      }

      unlock(canonic);
    }

    return canonic;
  }

  private boolean lock(K cached) {
    return transitionalCache.putIfAbsent(cached, cached) == null;
  }

  private void unlock(K cached) {
    boolean removed = transitionalCache.remove(cached, cached);
    assert removed;
  }

  /**
   * Unlike L2 and Q2, L1 elements can be removed independently from elements in Q1, when promoting L1 element to L2.
   * Afterwards, when L2 elements 'demoted' back to L1 get added to Q1, there are duplicating queue elements.
   * The Q1 is cleaned up after the number o promotes hits the threshold.
   */
  private void cleanupQ1() {
    promotesBeforeCleanup.set(promotesBeforeCleanupInitialValue);
    for (Iterator<K> it = firstLevelQueue.iterator(); it.hasNext();) {
      if (!firstLevelCache.containsKey(it.next())) {
        it.remove();
      }
    }
  }
}
