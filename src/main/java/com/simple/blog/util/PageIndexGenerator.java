package com.simple.blog.util;

import java.util.List;

public interface PageIndexGenerator {
  List<Integer> generateAdjacentRange(int current, int limit, int windowSize);
}
