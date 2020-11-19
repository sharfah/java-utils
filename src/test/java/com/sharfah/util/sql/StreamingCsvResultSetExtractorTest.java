package com.sharfah.util.sql;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class StreamingCsvResultSetExtractorTest {

  @Test
  public void testExtractData() throws SQLException {
    final var bos = new ByteArrayOutputStream();
    final var extractor = new StreamingCsvResultSetExtractor(bos);
    final var rs = MockResultSet.create(new String[] { "name", "age" },
        new Object[][] {
          { "Alice", 20 },
          { "Bob", 35 },
          { "Charles", 50 }
      });
    extractor.extractData(rs);
    final var csv = new String(bos.toByteArray());
    assertThat(csv, is(String.join(System.lineSeparator(), "name,age", "Alice,20", "Bob,35", "Charles,50") + System.lineSeparator()));
  }

  @Test
  public void testEmptyResultSet() throws SQLException {
    final var bos = new ByteArrayOutputStream();
    final var extractor = new StreamingCsvResultSetExtractor(bos);
    final var rs = MockResultSet.create(new String[] { "name", "age" },
        new Object[][] {});
    extractor.extractData(rs);
    final var csv = new String(bos.toByteArray());
    assertThat(csv, is("name,age" + System.lineSeparator()));
  }
}
