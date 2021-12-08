package com.simple.blog.util;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SequenceIndexGenerator implements PageIndexGenerator {
  public List<Integer> generateAdjacentRange(int current, int limit, int windowSize) {

    assert current <= limit;

    if (limit <= windowSize) {
      return IntStream.range(1, limit+1).boxed().collect(Collectors.toList());
    }

    final int half = windowSize / 2;
    int low = current - half;
    int high = current + half;

    if (low < 1) {
      high += Math.abs(low) + 1;
      low = 1;
    }

    if (high > limit) {
      low -= (high - limit);
      high = limit;
    }

    return IntStream.range(low, high+1).boxed().collect(Collectors.toList());
  }
}
