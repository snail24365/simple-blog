package com.simple.blog.util;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

@Log4j2
public class PageRangeGeneratorUtil {
  @Test
  public void test1() {
    SequenceIndexGenerator rangeGenerator = new SequenceIndexGenerator();
    log.info("Range Start");
    rangeGenerator.generateAdjacentRange(20,40,11).forEach(System.out::println);
    log.info("Range End");
  }

}
